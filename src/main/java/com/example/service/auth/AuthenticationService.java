package com.example.service.auth;

import com.example.dto.request.AuthenticationRequest;
import com.example.dto.request.SaveUser;
import com.example.dto.response.AuthenticationResponse;
import com.example.dto.response.GetUser;
import com.example.dto.response.RegisteredUser;
import com.example.mapper.UserMapper;
import com.example.persistence.entity.security.JwtToken;
import com.example.persistence.entity.security.User;
import com.example.persistence.repository.security.JwtTokenRepository;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenRepository jwtTokenRepository;

    @Autowired
    public AuthenticationService(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, JwtTokenRepository jwtTokenRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenRepository = jwtTokenRepository;
    }

    public RegisteredUser registerOneCustomer (SaveUser saveUser){
        User user = userService.registerOneCustomer(saveUser);
        String jwt= jwtService.generateToken(user,generateExtraClaims(user));
        saveUserToken(user,jwt);
        return UserMapper.toGetRegisteredUser(user,jwt);
    }
    public AuthenticationResponse login (AuthenticationRequest authenticationRequest){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.username(),
                    authenticationRequest.password()
            );
        this.authenticationManager.authenticate(authentication);
        User user = userService.findOneByUsername(authenticationRequest.username());
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        saveUserToken(user, jwt);
        return new AuthenticationResponse(jwt);
    }

    public boolean validateToken (String jwt){
        try {
            String extractUsername = jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
    public GetUser findLoggedInUser (){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return UserMapper.toGetDto(userService.findOneByUsername(username));
    }

    public void  logout (HttpServletRequest request){
        String jwt = jwtService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(jwt))return;
        Optional<JwtToken> token=jwtTokenRepository.findByToken(jwt);
        if (token.isPresent() ){
            JwtToken jwtToken = token.get();
            if (jwtToken.isValid()){
                jwtToken.setValid(false);
                jwtTokenRepository.save(jwtToken);
            }
        }

    }
    private void saveUserToken (User user,String jwt){
        JwtToken token=new JwtToken();
        token.setToken(jwt);
        token.setUser(user);
        token.setExpiration(jwtService.extractExpiration(jwt));
        token.setValid(true);
        jwtTokenRepository.save(token);
    }

    private Map<String, Object> generateExtraClaims (User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName()) ;
        extraClaims.put("role",user.getRole().getName()) ;
        extraClaims.put("authorities",user.getAuthorities()) ;
        return extraClaims;
    }
}

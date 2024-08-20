package com.example.config.filter;

import com.example.persistence.entity.security.JwtToken;
import com.example.persistence.entity.security.User;
import com.example.persistence.repository.security.JwtTokenRepository;
import com.example.service.UserService;
import com.example.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = jwtService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(jwt) ){
            filterChain.doFilter(request,response);
            return;
        }
        Optional<JwtToken> token = jwtTokenRepository.findByToken(jwt);
        if(!validateToken(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String username= jwtService.extractUsername(jwt);
        User user= userService.findOneByUsername(username);
        user.getAuthorities().forEach(System.out::println);
        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                username ,null,user.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        System.out.println("Setting authentication: " + authToken.getClass().getName());
        filterChain.doFilter(request,response);
        System.out.println("Security Context Authentication: " + SecurityContextHolder.getContext().getAuthentication());
    }

    private boolean validateToken(Optional<JwtToken> optionalJwt) {
        if (optionalJwt.isEmpty())return false;
        JwtToken jwtToken=optionalJwt.get();
        Date now=new Date(System.currentTimeMillis());
        boolean isValid=jwtToken.isValid()&&jwtToken.getExpiration().after(now);
        if (!isValid){
            System.out.println("Token Invaido");
            updateTokenStatus(jwtToken);
        }
        return isValid;
    }

    private void updateTokenStatus(JwtToken jwtToken) {
        jwtToken.setValid(false);
        jwtTokenRepository.save(jwtToken);
    }
}

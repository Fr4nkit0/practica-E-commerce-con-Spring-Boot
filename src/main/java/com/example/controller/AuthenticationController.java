package com.example.controller;
import com.example.dto.request.AuthenticationRequest;
import com.example.dto.response.AuthenticationResponse;
import com.example.dto.response.GetUser;
import com.example.dto.response.LogoutResponse;
import com.example.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private  final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate (@RequestParam String jwt){
        return ResponseEntity.ok(authenticationService.validateToken(jwt));
    }
    @GetMapping("/profile")
    public ResponseEntity<GetUser> findMyProfile (){
        return ResponseEntity.ok(authenticationService.findLoggedInUser());
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody @Valid AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout (HttpServletRequest request){
        return ResponseEntity.ok(new LogoutResponse("Logout exitoso"));
    }






}

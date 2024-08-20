package com.example.controller;


import com.example.dto.request.SaveUser;
import com.example.dto.response.RegisteredUser;
import com.example.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private  final AuthenticationService authenticationService;

    @Autowired
    public CustomerController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne (@RequestBody @Valid SaveUser saveUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerOneCustomer(saveUser));
    }

}

package com.example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


@Configuration
public class SecurityBeansInjector {

    private  final AuthenticationConfiguration authenticationConfiguration;

    public SecurityBeansInjector(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager () throws  Exception{
        return authenticationConfiguration.getAuthenticationManager();

    }
}

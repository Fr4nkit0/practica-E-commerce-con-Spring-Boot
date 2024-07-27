package com.example.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


public record SaveUser(
        @NotNull @NotEmpty(message = "Username Requerido") @Size(min=2 , max = 12)
        String username,
        @NotNull @NotEmpty(message = "Password Requerido") @Size(min=8 , max = 16)
        String password,
        @NotNull @NotEmpty(message = "Repeated Password Requered") @Size(min=8 , max = 16)
        String repeatedPassword,
        @NotNull @NotEmpty(message = "Phone Number Requered") @Size(max = 20)
        String phoneNumber,
        @NotNull @NotEmpty(message = "Email Requered") @Size(min=10, max = 250)
        String email
) implements Serializable {
}


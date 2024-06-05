package com.example.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto implements Serializable {
    @NotNull @NotEmpty(message = "Username Requerido") @Size(min=2 , max = 12)
    private String username;
    @NotNull @NotEmpty(message = "Password Requerido") @Size(min=8 , max = 16)
    private String password;
    @NotNull @NotEmpty(message = "Repeated Password Requerido") @Size(min=8 , max = 16)
    private String repeatedPassword;
    @NotNull @NotEmpty(message = "Phone Number Requerido") @Size(max = 20)
    private String phoneNumber;
    @NotNull @NotEmpty(message = "Email Requerido") @Size(min=10, max = 250)
    private String email;
}

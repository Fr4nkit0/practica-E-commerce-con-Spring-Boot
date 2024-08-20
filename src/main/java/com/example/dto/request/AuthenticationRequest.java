package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AuthenticationRequest(
        @NotBlank(message = "{generic.notBlank}")
        String username,
        @NotBlank(message = "{generic.notBlank}")
        String password
)implements Serializable {
}

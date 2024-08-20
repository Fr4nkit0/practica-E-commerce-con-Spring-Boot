package com.example.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;


public record SaveUser(
        @NotBlank(message ="{generic.notBlank}")
        @Size(min=3 , max = 12 , message = "{generic.size}")
        String username,
        @NotBlank(message ="{generic.notBlank}")
        @Size(min=3 , max = 40 , message = "{generic.size}")
        String name,
        @NotBlank(message ="{generic.notBlank}")
        @Size(min=8 , max = 16, message = "{generic.size}")
        String password,
        @JsonProperty("repeated_password")
        @NotBlank(message ="{generic.notBlank}")
        @Size(min=8 , max = 16, message = "{generic.size}")
        String repeatedPassword,
        @NotBlank(message ="{generic.notBlank}")
        @Size(min=7,max = 20, message = "{generic.size}")
        @JsonProperty("phone_number")
        String phoneNumber,
        @NotBlank(message ="{generic.notBlank}")
        @Size(min=10, max = 250, message = "{generic.size}")
        String email
) implements Serializable {
}


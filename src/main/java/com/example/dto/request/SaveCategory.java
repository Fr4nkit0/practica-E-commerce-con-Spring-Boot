package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record SaveCategory (
        @NotBlank(message = "{generic.notBlank}")
        String name
)implements Serializable {
}

package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SavePermission(
        @NotBlank (message ="{generic.notBlank}")
        String role,

        @NotBlank (message ="{generic.notBlank}")
        String operation
) {
}

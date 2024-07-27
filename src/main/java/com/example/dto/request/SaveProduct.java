package com.example.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;



public record SaveProduct (
        @NotNull @NotEmpty(message = "Name requerido")
        @Size(min = 2, max = 45)
        String name,
        @NotNull @Min(value = 10)
        String description,
        @DecimalMin(value = "0.01", message = "El precio no es valido")
        BigDecimal price,
        @Min(1)
        Integer categoryId
)implements Serializable {
}


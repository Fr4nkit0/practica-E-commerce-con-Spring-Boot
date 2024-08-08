package com.example.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;



public record SaveProduct (
        @NotBlank(message = "{generic.notBlank}")
        @Size(min = 2, max = 45,message ="{generic.size}")
        String name,
        @NotBlank(message = "{generic.notBlank}")
        @Size(min = 15,max = 255,message = "{generic.size}")
        String description,
        @DecimalMin(value = "0.01", message = "the minimum value is 0.01")
        BigDecimal price,
        @Min(value = 1,message = "{generic.min}")
        Integer categoryId
)implements Serializable {
}


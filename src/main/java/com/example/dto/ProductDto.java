package com.example.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {
    @NotNull @NotEmpty(message = "Name requerido")
    @Size(min = 2, max = 45)
    private String name;
    @NotNull
    private String description;
    @DecimalMin(value = "0.01", message = "El precio no es valido")
    private BigDecimal price;
    @Min(value = 1)
    private Integer categoryId;
}

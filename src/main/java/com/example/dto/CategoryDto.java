package com.example.dto;

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
public class CategoryDto implements Serializable {
    @NotNull
    @NotEmpty(message = "Nombre requerido")
    @Size(min = 2, max = 45)
    private String name;
}

package com.example.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    @NotNull @NotEmpty @Size(min=2 , max = 12)
    private String username;
    @NotNull @NotEmpty @Size(min=8 , max = 16)
    private String password;
    @NotNull @NotEmpty @Size(max = 20)
    private String phoneNumber;
    @NotNull @NotEmpty @Size(min=10, max = 250)
    private String email;
}

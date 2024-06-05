package com.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisteredUser implements Serializable {
    private Integer id ;
    private String name;
    private String jwt;
    private String role;
    private String email;
    private String phoneNumber;
}

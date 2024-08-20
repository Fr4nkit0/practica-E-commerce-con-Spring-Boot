package com.example.dto.response;



import java.io.Serializable;

public record RegisteredUser (
        Integer id,
        String username,
        String jwt,
        String role,
        String email,
        String phoneNumber
)implements Serializable {
}

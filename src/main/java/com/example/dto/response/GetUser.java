package com.example.dto.response;


import java.io.Serializable;

public record GetUser(
        String username,
        String email,
        String phoneNumber
)implements Serializable {
}

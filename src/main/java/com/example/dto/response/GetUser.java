package com.example.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record GetUser(
        String username,
        String email,
        @JsonProperty("phone_number") String phoneNumber
)implements Serializable {
}

package com.example.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record ShowPermission(
        int id,
        String operation,
        @JsonProperty("http_method") String httpMethod,
        String module,
        String role
) implements Serializable {
}

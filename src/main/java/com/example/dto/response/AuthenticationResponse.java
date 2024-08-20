package com.example.dto.response;

import java.io.Serializable;

public record AuthenticationResponse(
        String jwt
) implements Serializable {
}

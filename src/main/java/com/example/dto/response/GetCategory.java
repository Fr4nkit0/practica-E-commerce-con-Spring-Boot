package com.example.dto.response;

import java.io.Serializable;

public record GetCategory(
        String name
) implements Serializable {
}

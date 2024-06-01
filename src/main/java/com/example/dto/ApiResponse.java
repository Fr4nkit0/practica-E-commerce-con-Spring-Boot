package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor @NoArgsConstructor
public class ApiResponse implements Serializable {
    private String backendMessage;
    private String message;
    private String url;
    private String method;
    private LocalDateTime timeTamp;
}

package com.example.config.handler;

import com.example.dto.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.time.LocalDateTime;
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
       ApiResponse apiResponse= ApiResponse.builder()
                .timeTamp(LocalDateTime.now())
                .backendMessage(accessDeniedException.getMessage())
                .message("Acceso denegado. No tiene los permisos necesarios para acceder a esta funcion" +
                                "Por favor, contacta con el Administrador si crees que esto es un error")
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .build() ;



       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.registerModule(new JavaTimeModule());

       String apiResponseAsJson=objectMapper.writeValueAsString(apiResponse);

       response.setContentType(MediaType.APPLICATION_JSON_VALUE);
       response.setStatus(HttpStatus.FORBIDDEN.value());
       response.getWriter().write(apiResponseAsJson);

    }
}

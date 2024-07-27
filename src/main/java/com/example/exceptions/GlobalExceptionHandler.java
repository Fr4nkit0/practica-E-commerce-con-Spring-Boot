package com.example.exceptions;

import com.example.dto.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handlerGenericException ( HttpServletRequest request ,
                                                                 Exception exception){
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder()
                        .timeTamp(LocalDateTime.now())
                        .backendMessage(exception.getMessage())
                        .message("Error Interno en el Servidor")
                        .url(request.getRequestURL().toString())
                        .method(request.getMethod())
                        .build()) ;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlerMethodArgumentNotValidException (HttpServletRequest request,
                                                                               MethodArgumentNotValidException argumentNotValidException){
        Map<String,String> mistakes = new HashMap<>() ;
        argumentNotValidException.getBindingResult().getAllErrors().forEach((exception)->{
            String key =((FieldError)exception).getField() ;
            String valor = exception.getDefaultMessage() ;
            mistakes.put(key,valor) ;
        });
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder()
                        .timeTamp(LocalDateTime.now())
                        .backendMessage(mistakes.toString())
                        .message("Error en la request")
                        .url(request.getRequestURL().toString())
                        .method(request.getMethod())
                        .build()) ;
    }
}

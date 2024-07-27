package com.example.dto.response;



import java.io.Serializable;
import java.math.BigDecimal;


public record GetProduct(
        String name ,
        String description,
        BigDecimal price
) implements Serializable{
}

package com.example.dto.response;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;


public record GetProduct(
        String name ,
        String description,
        BigDecimal price,
        @JsonProperty("category_name") String categoryName
) implements Serializable{
}

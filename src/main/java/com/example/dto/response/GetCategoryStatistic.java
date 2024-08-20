package com.example.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record GetCategoryStatistic(
        String name,
        @JsonProperty("total_products") int totalProducts,
        @JsonProperty("average_price") Double  averagePrices,
        @JsonProperty("lowest_price")int lowestPrice,
        @JsonProperty("highest_price")int highestPrice
) implements Serializable {
    @Override
    public Double averagePrices() {
        return Double.parseDouble(String.format("%1.2f", averagePrices));
    }
}

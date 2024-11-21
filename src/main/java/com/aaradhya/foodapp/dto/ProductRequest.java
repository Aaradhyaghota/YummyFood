package com.aaradhya.foodapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record ProductRequest(
        @NotNull(message = "Product should be present")
        @NotEmpty(message = "Product should be present")
        @NotBlank(message = "Product should be present")
        @JsonProperty("name")
        String name,

        @NotNull(message = "Price should be present")
        @Positive(message = "Price should be a positive value")
        @JsonProperty("price")
        Double price
) {
}

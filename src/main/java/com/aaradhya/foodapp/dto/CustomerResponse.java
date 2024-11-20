package com.aaradhya.foodapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("id")
        long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("email")
        String email
) {
}

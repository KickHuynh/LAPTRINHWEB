package org.example.baitap_26_9_2025.graphql.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductInput(
        @JsonProperty("title") String title,
        @JsonProperty("quantity") Integer quantity,
        @JsonProperty("description") String description,
        @JsonProperty("price") Double price,
        @JsonProperty("categoryId") Long categoryId
) {
    @JsonCreator
    public ProductInput {}
}


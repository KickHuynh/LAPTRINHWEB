package org.example.baitap_26_9_2025.graphql.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record CategoryInput(
        @JsonProperty("name") String name,
        @JsonProperty("images") String images,
        @JsonProperty("userIds") Set<Long> userIds // danh sách user quản lý
) {
    @JsonCreator
    public CategoryInput {}
}


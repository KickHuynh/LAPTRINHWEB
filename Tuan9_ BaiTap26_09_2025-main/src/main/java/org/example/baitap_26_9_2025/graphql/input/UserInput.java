package org.example.baitap_26_9_2025.graphql.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInput(
        @JsonProperty("fullname") String fullname,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("phone") String phone
) {
    @JsonCreator
    public UserInput { }
}


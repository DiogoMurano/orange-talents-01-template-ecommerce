package br.com.zup.ecommerce.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse {

    @JsonProperty
    private final String token;

    @JsonProperty
    private final String type;

    public JwtResponse(String token) {
        this.token = token;
        this.type = "Bearer";
    }
}

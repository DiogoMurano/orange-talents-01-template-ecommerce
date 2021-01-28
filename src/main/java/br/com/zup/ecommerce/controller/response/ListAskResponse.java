package br.com.zup.ecommerce.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListAskResponse {

    @JsonProperty
    private final List<AskResponse> asks;

    public ListAskResponse(List<AskResponse> asks) {
        this.asks = asks;
    }
}

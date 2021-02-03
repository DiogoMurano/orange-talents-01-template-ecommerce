package br.com.zup.ecommerce.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class SellerRequest {

    @JsonProperty
    @NotNull
    private final Long purchaseId;

    @JsonProperty
    @NotNull
    private final Long sellerId;

    public SellerRequest(Long purchaseId, Long sellerId) {
        this.purchaseId = purchaseId;
        this.sellerId = sellerId;
    }
}

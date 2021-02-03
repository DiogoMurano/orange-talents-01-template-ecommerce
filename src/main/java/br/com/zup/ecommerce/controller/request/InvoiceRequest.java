package br.com.zup.ecommerce.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class InvoiceRequest {

    @JsonProperty
    @NotNull
    private final Long purchaseId;

    @JsonProperty
    @NotNull
    private final Long buyerId;

    public InvoiceRequest(Long purchaseId, Long buyerId) {
        this.purchaseId = purchaseId;
        this.buyerId = buyerId;
    }
}

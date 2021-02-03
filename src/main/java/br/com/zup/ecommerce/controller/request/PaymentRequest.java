package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.buy.Payment;
import br.com.zup.ecommerce.model.buy.PaymentStatus;
import br.com.zup.ecommerce.model.buy.Purchase;
import br.com.zup.ecommerce.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class PaymentRequest {

    @JsonProperty
    @NotNull
    private long purchaseId;

    @JsonProperty
    @NotNull
    @UniqueValue(domainClass = Payment.class, fieldName = "transactionId")
    private long transactionId;

    @JsonProperty
    @NotNull
    private Object status;

    public PaymentRequest(@NotNull long purchaseId, @NotNull long transactionId, @NotNull Object status) {
        this.purchaseId = purchaseId;
        this.transactionId = transactionId;
        this.status = status;
    }

    public Payment toModel(Purchase purchase, PaymentStatus status) {
        return new Payment(purchase, transactionId, status);
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public Object getStatus() {
        return status;
    }
}

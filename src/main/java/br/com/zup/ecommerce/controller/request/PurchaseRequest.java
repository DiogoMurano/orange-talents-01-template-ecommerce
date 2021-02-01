package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.buy.FinalizingPurchase;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.provider.gateway.GatewayType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PurchaseRequest {

    @JsonProperty
    @NotNull
    private final Long productId;

    @JsonProperty
    @Positive
    private final int quantity;

    @JsonProperty
    @NotBlank
    private final String paymentGateway;

    public PurchaseRequest(Long productId, int quantity, String paymentGateway) {
        this.productId = productId;
        this.quantity = quantity;
        this.paymentGateway = paymentGateway;
    }

    public FinalizingPurchase toModel(GatewayType gatewayType, Product product) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new FinalizingPurchase(quantity, gatewayType, product, user);
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }
}

package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.product.Ask;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AskRequest {

    @NotBlank
    @JsonProperty
    private final String title;

    @NotNull
    @JsonProperty
    private final Long productId;

    public AskRequest(String title, Long productId) {
        this.title = title;
        this.productId = productId;
    }

    public Ask toModel(User author, Product product) {
        return new Ask(title, author, product);
    }

    public Long getProductId() {
        return productId;
    }
}

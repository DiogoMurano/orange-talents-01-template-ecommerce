package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.product.Review;
import br.com.zup.ecommerce.model.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class ReviewRequest {

    @Min(1)
    @Max(5)
    @JsonProperty
    private final int note;

    @NotBlank
    @JsonProperty
    private final String title;

    @NotBlank
    @Size(max = 500)
    @JsonProperty
    private final String description;

    @NotNull
    @JsonProperty
    private final Long productId;

    public ReviewRequest(int note, String title, String description, Long productId) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.productId = productId;
    }

    public Review toModel(Product product, User user) {
        return new Review(note, title, description, product, user);
    }

    public Long getProductId() {
        return productId;
    }
}

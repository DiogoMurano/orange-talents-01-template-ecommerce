package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.ProductImage;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddImageResponse {

    @JsonProperty
    private final Long imageId;

    @JsonProperty
    private final String link;

    public AddImageResponse(ProductImage productImage) {
        this.imageId = productImage.getId();
        this.link = productImage.getLink();
    }
}

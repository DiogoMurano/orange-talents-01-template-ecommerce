package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.ProductImage;

public class AddImageResponse {

    private Long imageId;

    private String link;

    public AddImageResponse(ProductImage productImage) {
        this.imageId = productImage.getId();
        this.link = productImage.getLink();
    }

    public String getLink() {
        return link;
    }

    public Long getImageId() {
        return imageId;
    }
}

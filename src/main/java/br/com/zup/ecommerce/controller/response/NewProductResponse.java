package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class NewProductResponse {

    @JsonProperty
    private final Long id;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final BigDecimal value;

    @JsonProperty
    private final int quantity;

    @JsonProperty
    private final String description;

    @JsonProperty
    private final List<FeatureResponse> features;

    @JsonProperty
    private final CategoryResponse category;

    public NewProductResponse(Product product) {
        this.id  = product.getId();
        this.name = product.getName();
        this.value = product.getValue();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.features = product.getFeatures().stream().map(FeatureResponse::new).collect(Collectors.toList());
        this.category = new CategoryResponse(product.getCategory());
    }
}

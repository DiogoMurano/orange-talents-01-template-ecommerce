package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    private String name;

    private BigDecimal value;

    private int quantity;

    private String description;

    private List<FeatureResponse> features;

    private CategoryResponse category;

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.value = product.getValue();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.features = product.getFeatures().stream().map(FeatureResponse::new).collect(Collectors.toList());
        this.category = new CategoryResponse(product.getCategory());
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public List<FeatureResponse> getFeatures() {
        return features;
    }

    public CategoryResponse getCategory() {
        return category;
    }
}

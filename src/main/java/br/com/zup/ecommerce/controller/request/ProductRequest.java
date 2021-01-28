package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.product.Category;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRequest {

    @JsonProperty
    @NotBlank
    private final String name;

    @JsonProperty
    @NotNull
    @Positive
    private final BigDecimal value;

    @JsonProperty
    @NotNull
    @Positive
    private final int quantity;

    @JsonProperty
    @NotBlank
    @Size(max = 1000)
    private final String description;

    @JsonProperty
    @OneToMany
    @NotNull
    @Size(min = 3)
    private final List<FeatureRequest> features;

    @JsonProperty
    @NotNull
    private final String categoryName;

    public ProductRequest(String name, BigDecimal value,
                          int quantity, String description, List<FeatureRequest> features, String categoryName) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.description = description;
        this.features = features;
        this.categoryName = categoryName;
    }

    public Product toModel(Category category, User user) {
        return new Product(name, value, quantity, description, features.stream().map(FeatureRequest::toModel)
                .collect(Collectors.toList()), category, user);
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

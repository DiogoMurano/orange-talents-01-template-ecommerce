package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.product.Category;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;

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

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal value;

    @NotNull
    @Positive
    private int quantity;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @OneToMany
    @NotNull
    @Size(min = 3)
    private List<FeatureRequest> features = Collections.emptyList();

    @NotNull
    private String categoryName;

    public Product createModel(Category category, User user) {
        return new Product(name, value, quantity, description, features.stream().map(FeatureRequest::createModel)
                .collect(Collectors.toList()), category, user);
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

    public List<FeatureRequest> getFeatures() {
        return features;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

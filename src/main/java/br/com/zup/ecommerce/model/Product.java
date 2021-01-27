package br.com.zup.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    private List<Feature> features = Collections.emptyList();

    @ManyToOne
    @NotNull
    private Category category;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne
    @NotNull
    private User user;

    public Product(String name, BigDecimal value, int quantity, String description, List<Feature> features, Category category, User user) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.description = description;
        this.features = features;
        this.category = category;
        this.user = user;
    }

    public Product() {
    }

    public Long getId() {
        return id;
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

    public List<Feature> getFeatures() {
        return features;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }
}

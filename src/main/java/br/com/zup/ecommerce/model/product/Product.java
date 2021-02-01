package br.com.zup.ecommerce.model.product;

import br.com.zup.ecommerce.model.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    private final LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne
    @NotNull
    private User user;

    @OneToMany(mappedBy = "product")
    @NotNull
    private final List<ProductImage> images = Collections.emptyList();

    @OneToMany(mappedBy = "product")
    @NotNull
    private final List<Review> reviews = Collections.emptyList();

    @OneToMany(mappedBy = "product")
    @NotNull
    private final List<Ask> asks = Collections.emptyList();

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

    public List<ProductImage> getImages() {
        return images;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Ask> getAsks() {
        return asks;
    }

    public void removeQuantity(int quantity) {
        if (this.quantity < quantity) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid quantity.");
        }
        this.quantity -= quantity;
    }
}

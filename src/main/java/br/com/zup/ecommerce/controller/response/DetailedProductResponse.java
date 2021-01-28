package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.product.ProductImage;
import br.com.zup.ecommerce.model.product.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DetailedProductResponse {

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
    private final CategoryResponse category;

    @JsonProperty
    @JsonFormat(pattern = "dd/MM/yyyy - HH:mm", shape = JsonFormat.Shape.STRING)
    private final LocalDateTime createdAt;

    @JsonProperty
    private final BigDecimal averageReviewNote;

    @JsonProperty
    private final int countReviews;

    @JsonProperty
    private final List<FeatureResponse> features;

    @JsonProperty
    private final List<AddImageResponse> images;

    @JsonProperty
    private final List<ReviewResponse> reviews;

    @JsonProperty
    private final List<AskResponse> asks;

    public DetailedProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.value = product.getValue();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.category = new CategoryResponse(product.getCategory());
        this.createdAt = product.getCreatedAt();
        this.features = product.getFeatures().stream().map(FeatureResponse::new).collect(Collectors.toList());
        this.images = product.getImages().stream().map(AddImageResponse::new).collect(Collectors.toList());
        this.reviews = product.getReviews().stream().map(ReviewResponse::new).collect(Collectors.toList());
        this.asks = product.getAsks().stream().map(AskResponse::new).collect(Collectors.toList());

        this.countReviews = reviews.size();
        this.averageReviewNote = averageNote(product.getReviews());
    }

    private BigDecimal averageNote(List<Review> reviews) {
        int totalValue = 0;

        for (Review review : reviews) {
            totalValue += review.getNote();
        }


        return new BigDecimal(totalValue).divide(BigDecimal.valueOf(countReviews == 0 ? 1 : countReviews), 2,
                RoundingMode.HALF_EVEN);
    }

}

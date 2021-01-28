package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewResponse {

    @JsonProperty
    private final int note;

    @JsonProperty
    private final String title;

    @JsonProperty
    private final String description;

    @JsonProperty
    private final Long product;

    @JsonProperty
    private final String author;

    public ReviewResponse(Review review) {
        this.note = review.getNote();
        this.title = review.getTitle();
        this.description = review.getDescription();
        this.product = review.getProduct().getId();
        this.author = review.getAuthor().getLogin();
    }
}

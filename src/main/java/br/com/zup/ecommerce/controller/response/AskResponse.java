package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.Ask;
import br.com.zup.ecommerce.model.product.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AskResponse {

    @JsonProperty
    private final String title;

    @JsonProperty
    private final Long productId;

    @JsonProperty
    private final String author;


    public AskResponse(Ask ask) {
        this.title = ask.getTitle();
        this.productId = ask.getProduct().getId();
        this.author = ask.getAuthor().getLogin();
    }
}

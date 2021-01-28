package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.Feature;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeatureResponse {

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String description;

    public FeatureResponse(Feature feature) {
        this.name = feature.getName();
        this.description = feature.getDescription();
    }
}

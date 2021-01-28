package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.product.Feature;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class FeatureRequest {

    @JsonProperty
    @NotBlank
    private final String key;

    @JsonProperty
    @NotBlank
    private final String value;

    public FeatureRequest(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Feature toModel() {
        return new Feature(key, value);
    }
}

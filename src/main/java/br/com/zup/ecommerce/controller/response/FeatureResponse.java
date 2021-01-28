package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.Feature;

public class FeatureResponse {

    private String name;

    private String description;

    public FeatureResponse(Feature feature) {
        this.name = feature.getName();
        this.description = feature.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

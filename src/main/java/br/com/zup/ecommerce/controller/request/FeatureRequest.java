package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.Feature;

public class FeatureRequest {

    private String key;

    private String value;

    public Feature createModel() {
        return new Feature(key, value);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

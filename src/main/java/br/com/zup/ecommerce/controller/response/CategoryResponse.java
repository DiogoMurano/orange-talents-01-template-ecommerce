package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.product.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryResponse {

    @JsonProperty
    private final String name;

    @JsonProperty
    private CategoryResponse motherCategory;

    public CategoryResponse(Category category) {
        this.name = category.getName();
        this.motherCategory = null;

        if(category.getMotherCategory() != null) {
            this.motherCategory = new CategoryResponse(category.getMotherCategory());
        }
    }
}

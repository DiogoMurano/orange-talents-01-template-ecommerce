package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.product.Category;
import br.com.zup.ecommerce.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    @JsonProperty
    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private final String name;

    @JsonProperty
    private final String motherCategory;

    public CategoryRequest(String name, String motherCategory) {
        this.name = name;
        this.motherCategory = motherCategory;
    }

    public Category toModel(Category motherCategory) {
        return new Category(name, motherCategory);
    }

    public String getName() {
        return name;
    }

    public String getMotherCategory() {
        return motherCategory;
    }
}

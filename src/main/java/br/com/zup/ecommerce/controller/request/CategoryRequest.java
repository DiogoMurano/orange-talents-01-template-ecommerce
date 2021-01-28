package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.product.Category;
import br.com.zup.ecommerce.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private final String name;

    private final String motherCategory;

    public CategoryRequest(@NotBlank String name, String motherCategory) {
        this.name = name;
        this.motherCategory = motherCategory;
    }

    public String getName() {
        return name;
    }

    public String getMotherCategory() {
        return motherCategory;
    }
}

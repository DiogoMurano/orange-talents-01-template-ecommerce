package br.com.zup.ecommerce.controller.request;

import br.com.zup.ecommerce.model.Category;
import br.com.zup.ecommerce.validation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    private String motherCategory;

    public String getName() {
        return name;
    }

    public String getMotherCategory() {
        return motherCategory;
    }
}

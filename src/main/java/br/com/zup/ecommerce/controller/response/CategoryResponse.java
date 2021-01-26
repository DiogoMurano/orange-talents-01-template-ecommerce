package br.com.zup.ecommerce.controller.response;

import br.com.zup.ecommerce.model.Category;

public class CategoryResponse {

    private String name;
    private CategoryResponse motherCategory;

    public CategoryResponse(Category category) {
        this.name = category.getName();
        this.motherCategory = null;

        if(category.getMotherCategory() != null) {
            this.motherCategory = new CategoryResponse(category.getMotherCategory());
        }
    }

    public String getName() {
        return name;
    }

    public CategoryResponse getMotherCategory() {
        return motherCategory;
    }
}

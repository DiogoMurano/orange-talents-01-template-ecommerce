package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.CategoryRequest;
import br.com.zup.ecommerce.controller.response.CategoryResponse;
import br.com.zup.ecommerce.model.product.Category;
import br.com.zup.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository repository;

    @Autowired
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createNewCategory(@RequestBody @Valid CategoryRequest request) {
        Category motherCategory = repository.findByName(request.getMotherCategory()).orElse(null);

        Category category = new Category(request.getName(), motherCategory);
        repository.save(category);

        return ResponseEntity.ok(new CategoryResponse(category));
    }

}

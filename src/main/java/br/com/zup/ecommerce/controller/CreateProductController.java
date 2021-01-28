package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.ProductRequest;
import br.com.zup.ecommerce.controller.response.NewProductResponse;
import br.com.zup.ecommerce.model.product.Category;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.repository.CategoryRepository;
import br.com.zup.ecommerce.repository.FeatureRepository;
import br.com.zup.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class CreateProductController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;

    @Autowired
    public CreateProductController(CategoryRepository categoryRepository,
                                   ProductRepository productRepository, FeatureRepository featureRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.featureRepository = featureRepository;
    }

    @PostMapping("/new")
    public ResponseEntity<NewProductResponse> createNewProduct(@RequestBody @Valid ProductRequest request) {
        Category category = categoryRepository.findByName(request.getCategoryName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found."));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = request.toModel(category, user);

        product.getFeatures().forEach(featureRepository::save);
        productRepository.save(product);

        return ResponseEntity.ok(new NewProductResponse(product));
    }

}

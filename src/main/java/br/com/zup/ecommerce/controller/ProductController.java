package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.ProductRequest;
import br.com.zup.ecommerce.controller.response.DetailedProductResponse;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailedProductResponse> detailResponse(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        return ResponseEntity.ok(new DetailedProductResponse(product));
    }
}

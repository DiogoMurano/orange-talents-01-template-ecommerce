package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.ProductRequest;
import br.com.zup.ecommerce.controller.response.AddImageResponse;
import br.com.zup.ecommerce.controller.response.ProductResponse;
import br.com.zup.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createNewProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createNewProduct(request));
    }

    @PostMapping(value = "/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddImageResponse> uploadImage(@NotNull MultipartFile image, @NotNull Long productId) {
        return ResponseEntity.ok(productService.uploadImage(image, productId));
    }
}

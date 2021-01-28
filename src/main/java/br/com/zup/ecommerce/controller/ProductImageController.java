package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.response.AddImageResponse;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.product.ProductImage;
import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.provider.UploadImageProvider;
import br.com.zup.ecommerce.repository.ProductImageRepository;
import br.com.zup.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product/image")
public class ProductImageController {

    private final ProductRepository productRepository;
    private final ProductImageRepository imageRepository;
    private final UploadImageProvider imageProvider;

    @Autowired
    public ProductImageController(ProductRepository productRepository,
                                  ProductImageRepository imageRepository, UploadImageProvider imageProvider) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.imageProvider = imageProvider;
    }

    @PostMapping(value = "/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddImageResponse> uploadImage(@NotNull MultipartFile image, @NotNull Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!product.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This product isn't yours.");
        }

        ProductImage productImage = new ProductImage(product, imageProvider.uploadImage(image));
        imageRepository.save(productImage);

        return ResponseEntity.ok(new AddImageResponse(productImage));
    }

}

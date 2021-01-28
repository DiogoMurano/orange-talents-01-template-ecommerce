package br.com.zup.ecommerce.service;

import br.com.zup.ecommerce.controller.request.ProductRequest;
import br.com.zup.ecommerce.controller.response.AddImageResponse;
import br.com.zup.ecommerce.controller.response.ProductResponse;
import br.com.zup.ecommerce.model.Category;
import br.com.zup.ecommerce.model.Product;
import br.com.zup.ecommerce.model.ProductImage;
import br.com.zup.ecommerce.model.User;
import br.com.zup.ecommerce.provider.UploadImageProvider;
import br.com.zup.ecommerce.repository.CategoryRepository;
import br.com.zup.ecommerce.repository.FeatureRepository;
import br.com.zup.ecommerce.repository.ProductImageRepository;
import br.com.zup.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;
    private final ProductImageRepository imageRepository;
    private final UploadImageProvider imageProvider;

    @Autowired
    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository, FeatureRepository
            featureRepository, ProductImageRepository imageRepository, UploadImageProvider imageProvider) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.featureRepository = featureRepository;
        this.imageRepository = imageRepository;
        this.imageProvider = imageProvider;
    }

    public ProductResponse createNewProduct(ProductRequest request) {
        Category category = categoryRepository.findByName(request.getCategoryName()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found."));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = request.createModel(category, user);

        product.getFeatures().forEach(featureRepository::save);
        productRepository.save(product);

        return new ProductResponse(product);
    }

    public AddImageResponse uploadImage(MultipartFile file, @NotNull Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!product.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This product isn't yours.");
        }

        ProductImage productImage = new ProductImage(product, imageProvider.uploadImage(file));
        imageRepository.save(productImage);

        return new AddImageResponse(productImage);
    }

}

package br.com.zup.ecommerce.controller;

import br.com.zup.ecommerce.controller.request.ReviewRequest;
import br.com.zup.ecommerce.controller.response.ReviewResponse;
import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.product.Review;
import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.repository.ProductRepository;
import br.com.zup.ecommerce.repository.ReviewRepository;
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
@RequestMapping("/product/review")
public class ProductReviewController {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ProductReviewController(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> sendNewReview(@RequestBody @Valid ReviewRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Review review = request.toModel(product, user);
        reviewRepository.save(review);

        return ResponseEntity.ok(new ReviewResponse(review));
    }

}

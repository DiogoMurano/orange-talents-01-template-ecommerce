package br.com.zup.ecommerce.service;

import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
    }

    public void checkAvailable(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "This product doesn't have enough stock for this request.");
        }
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }
}

package br.com.zup.ecommerce.repository;

import br.com.zup.ecommerce.model.product.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

}

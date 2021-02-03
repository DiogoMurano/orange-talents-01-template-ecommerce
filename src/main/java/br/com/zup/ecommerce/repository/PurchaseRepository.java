package br.com.zup.ecommerce.repository;

import br.com.zup.ecommerce.model.buy.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}

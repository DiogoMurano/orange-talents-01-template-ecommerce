package br.com.zup.ecommerce.repository;

import br.com.zup.ecommerce.model.buy.FinalizingPurchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalizingPurchaseRepository extends CrudRepository<FinalizingPurchase, Long> {
}

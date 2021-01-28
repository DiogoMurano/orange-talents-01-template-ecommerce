package br.com.zup.ecommerce.repository;

import br.com.zup.ecommerce.model.product.Ask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AskRepository extends CrudRepository<Ask, Long> {


}

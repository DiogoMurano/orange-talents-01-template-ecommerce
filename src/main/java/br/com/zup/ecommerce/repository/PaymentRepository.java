package br.com.zup.ecommerce.repository;

import br.com.zup.ecommerce.model.buy.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}

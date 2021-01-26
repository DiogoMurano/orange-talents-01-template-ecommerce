package br.com.zup.ecommerce.repository;

import br.com.zup.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
package br.com.zup.ecommerce.repository;

import br.com.zup.ecommerce.model.Feature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long> {
}

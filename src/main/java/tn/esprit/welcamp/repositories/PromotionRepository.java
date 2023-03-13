package tn.esprit.welcamp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcamp.entities.Promotion;
@Repository
public interface PromotionRepository extends CrudRepository<Promotion,Long> {
}

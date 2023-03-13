package tn.esprit.welcamp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcamp.entities.CategoryTools;

@Repository
public interface CategoryToolsRepository extends CrudRepository<CategoryTools,Long> {
}

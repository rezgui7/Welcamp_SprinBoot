package tn.esprit.welcamp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcamp.entities.Command;

@Repository
public interface OrderRepository extends CrudRepository<Command,Long> {
}

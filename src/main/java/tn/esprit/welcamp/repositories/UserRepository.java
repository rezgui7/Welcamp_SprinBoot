package tn.esprit.welcamp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcamp.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
}

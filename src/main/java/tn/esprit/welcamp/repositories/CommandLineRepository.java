package tn.esprit.welcamp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.User;

import java.util.List;

@Repository
public interface CommandLineRepository extends CrudRepository<CommandLine,Long> {
    List<CommandLine> findAllByUserOrderByCreatedDateDesc(User user);
}

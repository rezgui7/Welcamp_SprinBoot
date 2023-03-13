package tn.esprit.welcamp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.welcamp.entities.ImageData;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}

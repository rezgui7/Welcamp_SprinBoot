package tn.esprit.welcamp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.entities.ToolOffer;

import java.util.List;

@Repository
public interface ToolOfferRepository extends CrudRepository<ToolOffer,Long> {
    @Query("select t from ToolOffer t where t.toolName=?1")
    List<ToolOffer> findByName(String toolName);

    @Query("select t from ToolOffer t ,CategoryTools c WHERE t.categoryTools.idCategoryTools=c.idCategoryTools and t.toolName=?1 or t.brand=?2 or t.price=?3 or c.category=?4")
    List<ToolOffer> multiFind(String toolName, String brand, double price, String categoryTools);

    @Query("select t from ToolOffer t WHERE t.categoryTools.category=?1")
    List<ToolOffer> searchByCategoryTools(String category);
}

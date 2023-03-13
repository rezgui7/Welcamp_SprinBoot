package tn.esprit.welcamp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.welcamp.entities.Promotion;
import tn.esprit.welcamp.entities.ToolOffer;
import tn.esprit.welcamp.services.PromotionService;

import java.util.List;

@RestController
@RequestMapping("/promotion")
public class PromotionRestController {
    @Autowired
    PromotionService ps;

     @PostMapping("/addPromotion")
    public Promotion addPromotion(@RequestBody Promotion p){return ps.addPromotion(p);}

    @GetMapping("/displayPromotions")
    public List<Promotion> displayPromotions(){ return (List<Promotion>) ps.displayPromotions();}

    @GetMapping("/displayPromotion")
    public Promotion displayPromotion(@RequestParam long idPromotion){return ps.displayPromotion((int)idPromotion);}


    @PutMapping("/updatePromotion")
    public Promotion modifiePromotion(@RequestBody Promotion p){ return ps.modifiePromotion(p); }


    @DeleteMapping("/deletePromotion")
    public void deletePromotion(@RequestBody Promotion p){ps.deletePromotion(p);}

    @PutMapping("/assignPromotionToCategory")
    public void assign(@RequestParam long idCategory, @RequestBody Promotion p){
        ps.assignPromotionToCategory(idCategory,p);
    }

    @PutMapping("/assignPromotionToTool")
    public void assignToTool(@RequestParam long idTool, @RequestBody Promotion p){
        ps.assignPromotionToTool(idTool,p);
    }

    @PutMapping("/ApplyPromotion")
    public String applyPromo(@RequestBody Promotion p,@RequestParam long idTool) {
         return ps.applyPromotion(p,idTool);
    }

    @PutMapping("/applyPromoForCategory")
    public String applyPromoCategory(@RequestBody Promotion p,@RequestParam long idCategory){
         return ps.applyPromotionOnCategoty(p,idCategory);
    }

    @PutMapping("/applyPromoForAllCategory")
    public String applyPromoAllCategory(@RequestBody Promotion p){
        return ps.applyPromotionOnAllCategories(p);
    }

    @PutMapping("/loyaltyPoints")
    public void applyLoyaltyPoints(@RequestParam long idUser){
        ps.loyaltyPoints(idUser);
    }
}

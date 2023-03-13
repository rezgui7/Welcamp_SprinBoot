package tn.esprit.welcamp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.ToolOffer;
import tn.esprit.welcamp.services.ToolOfferService;

import java.util.List;

@RestController
@RequestMapping("/toolOffer")
public class ToolOfferRestController {
    @Autowired
    ToolOfferService tos;


    @PostMapping("/addToolOffer")
    public ToolOffer addToolOffer(@RequestBody ToolOffer to){return tos.addToolOffer(to);}

    @GetMapping("/displayToolOffers")
    public List<ToolOffer> displayToolOffers(){ return (List<ToolOffer>) tos.displayToolOffers();}

    @GetMapping("/displayToolOffer")
    public ToolOffer displayToolOffer(@RequestParam long idToolOffer){return tos.displayToolOffer((int)idToolOffer);}

    @PutMapping("/updateToolOffer")
    public ToolOffer modifieToolOffer(@RequestBody ToolOffer o){ return tos.modifieToolOffer(o); }

    @DeleteMapping("/deleteToolOffer")
    public void deleteToolOffer(@RequestBody ToolOffer o){tos.deleteToolOffer(o);}

    @GetMapping("/searchTools")
    public List<ToolOffer> getToolsByName(@RequestParam("tool_name") String toolName){
        return tos.searchToolOfferByName(toolName);
    }

    @GetMapping("/MultiSearchTools")
    public List<ToolOffer> MultiSearchTools(@RequestParam String toolName, @RequestParam String brand, @RequestParam double price, @RequestParam String categoryTools){
        return tos.searchMultiCritere(toolName,brand,price,categoryTools);
    }
    @GetMapping("/searchByCategoryTools")
    public List<ToolOffer> searchByCategoryTools(@RequestParam String categoryTools){
        return tos.searchByCategoryTools(categoryTools);
    }


    @PutMapping("/assignToolOfferToCategory")
    public ToolOffer assign(@RequestParam Integer idCategory,@RequestBody ToolOffer t){
        return tos.assignCategoryToOffer(idCategory,t);
    }

//    @PostMapping("/assignToolToCommandline")
//    public CommandLine assign(@RequestBody CommandLine c, @RequestParam long idtl){
//        return tos.addAndAssignToolOfferToCommandline(c,idtl);
//    }

}

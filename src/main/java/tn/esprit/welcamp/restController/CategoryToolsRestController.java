package tn.esprit.welcamp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.services.CategoryToolsService;

import java.util.List;

@RestController
@RequestMapping("/categoryTools")
public class CategoryToolsRestController {
    @Autowired
    CategoryToolsService cts;

    @PostMapping("/addCategoryTools")
    public CategoryTools addCategoryTools(@RequestBody CategoryTools ct){return cts.addCategoryTools(ct);}

    @GetMapping("/displaycategories")
    public List<CategoryTools> displayCategoryTools(){ return (List<CategoryTools>) cts.displayCategoryTools();}

    @GetMapping("/displaycategory")
    public CategoryTools displayCategoryTool(@RequestParam long idCategoryTools){return cts.displayCategoryTools((int)idCategoryTools);}

    @PutMapping("/updateCategory")
    public CategoryTools modifieCategoryTools(@RequestBody CategoryTools ct){ return cts.modifieCategoryTools(ct); }

    @DeleteMapping("/deleteCategory")
    public void deleteCategoryTools(@RequestBody CategoryTools ct){cts.deleteCategoryTools(ct);}

}

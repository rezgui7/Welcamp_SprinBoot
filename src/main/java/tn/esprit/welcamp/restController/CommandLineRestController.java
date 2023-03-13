package tn.esprit.welcamp.restController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.welcamp.dto.cart.CartDto;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.ToolOffer;
import tn.esprit.welcamp.services.CommandLineService;

import java.util.List;

@RestController
@RequestMapping("/commandLine")
public class CommandLineRestController {
    @Autowired
    CommandLineService cls;
    @PostMapping("/addCommandLine")
    public CommandLine addCommandLine(@RequestBody CommandLine ct){return cls.addCommandLine(ct);}

    @GetMapping("/displayCommandLine")
    public List<CommandLine> displayCommandLine(){ return (List<CommandLine>) cls.displayCommandLine();}

    @GetMapping("/displayCommandLines")
    public CommandLine displayCommandLines(@RequestParam long idCommandLine){return cls.displayCommandLines((int)idCommandLine);}


    @PutMapping("/updateCommandLine")
    public CommandLine modifieCommandLine(@RequestBody CommandLine ct){ return cls.modifieCommandLine(ct); }


    @DeleteMapping("/deleteCommandLine")
    public void deleteCommandLine(@RequestBody CommandLine ct){cls.deleteCommandLine(ct);}

    @PostMapping("/add")
    public String addToCart(@RequestBody CommandLine c,@RequestParam long idTool, @RequestParam int idU) {
        cls.addToCart(c,idTool,idU);
        return  "Added to cart";
    }
    @PostMapping("/add2")
    public String addToCart2(@RequestParam long idTool,@RequestParam long idC) {
        cls.addToCart2(idTool,idC);
        return  "Added to cart";
    }
    // get all cart items for a user
    @GetMapping("/displayCartItems")
    public CartDto getCartItems(@RequestParam Integer idU) {


        // get cart items
        //CartDto cartDto = cls.listCartItems(idU);
        //return new ResponseEntity<>(cartDto, HttpStatus.OK);

        return cls.listCartItems(idU);
    }

    // delete a cart item for a user

    @DeleteMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam long toolId, @RequestParam Integer iduser) {

        cls.deleteCartItem(toolId, iduser);

        return "Tool has been removed";

    }
}

package tn.esprit.welcamp.restController;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.welcamp.dto.CheckOut.CheckOutToolsDto;
import tn.esprit.welcamp.dto.CheckOut.StripeResponse;
import tn.esprit.welcamp.entities.Command;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderRestController {
    @Autowired
    OrderService os;

    @PostMapping("/addOrder")
    public Command addOrder(@RequestBody Command o){return os.addOrder(o);}

    @GetMapping("/displayOrders")
    public List<Command> displayOrders(){ return (List<Command>) os.displayOrders();}

    @GetMapping("/displayOrder")
    public Command displayOrder(@RequestParam long idOrder){return os.displayOrder((int)idOrder);}


    @PutMapping("/updateOrder")
    public Command modifieOrder(@RequestBody Command o){ return os.modifieOrder(o); }


    @DeleteMapping("/deleteOrder")
    public void deleteOrder(@RequestBody Command o){os.deleteOrder(o);}

    @PostMapping("/assignOfferToCommandline")
    public CommandLine assign(@RequestBody CommandLine c, @RequestParam long idO){
        return os.addAndAssignOfferToCommandline(c,idO);
    }
    // stripe session checkout api

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckOutToolsDto> checkoutItemDtoList,@RequestParam Integer id)
            throws StripeException {
        Session session = os.createSession(checkoutItemDtoList,id);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}

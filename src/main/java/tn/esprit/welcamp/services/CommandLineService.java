package tn.esprit.welcamp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.welcamp.dto.cart.CartDto;
import tn.esprit.welcamp.dto.cart.CartItemDto;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.ToolOffer;
import tn.esprit.welcamp.entities.User;
import tn.esprit.welcamp.exceptions.CustomException;
import tn.esprit.welcamp.repositories.CommandLineRepository;
import tn.esprit.welcamp.repositories.ToolOfferRepository;
import tn.esprit.welcamp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service //ou @Component
@Transactional
public class CommandLineService implements ICommandLineService {
    @Autowired
    CommandLineRepository clr;
    @Autowired
    ToolOfferRepository tor;
    @Autowired
    ToolOfferService toolOfferService;
    @Autowired
    UserRepository userRepository;



    @Override
    public CommandLine addCommandLine(CommandLine cl){return clr.save(cl);}



    @Override
    public List<CommandLine> displayCommandLine(){ return (List<CommandLine>) clr.findAll();}


    @Override
    public CommandLine displayCommandLines(long idCommandLine){return clr.findById(idCommandLine).get();}

    @Override
    public CommandLine modifieCommandLine(CommandLine cl){ return clr.save(cl); }

    @Override
    public void deleteCommandLine(CommandLine cl){clr.delete(cl);}

//-----------------------------------------ADD TO CART-----------------------------------

    @Override
    public void addToCart(CommandLine commandLine,long idTool,long idUser) {

        // validate if the product id is valid
        ToolOffer product = toolOfferService.findById(idTool);
        User u = userRepository.findById((int) idUser).get();

        CommandLine cart = new CommandLine();
        cart.setToolOffer(product);
        cart.setUser(u);
        cart.setEmpty(false);
        cart.setToolsQuantity(commandLine.getToolsQuantity());
        cart.setCreatedDate(new Date());
        //product.getCommandLines().add(cart);
        // save the cart
        clr.save(cart);
    }

    public void addToCart2(long idTool,long idCart) {
        double totalPrice=0;
        int totalQuantity=0;

        // validate if the product id is valid
        CommandLine c = clr.findById(idCart).get();
        ToolOffer product = toolOfferService.findById(idTool);

        for (CommandLine cl: clr.findAll()) {
            if (cl.getIdCart()!=idCart){
                CommandLine cart = new CommandLine();
                cart.setToolOffer(product);
                cart.setEmpty(false);
                cart.setToolsQuantity(1);
                cart.setTotalPrice(product.getPrice());
                cart.setCreatedDate(new Date());
                //product.getCommandLines().add(cart);
                // save the cart
                clr.save(cart);
            }

                totalPrice += cl.getTotalPrice();
                totalPrice+=product.getPrice();
                c.setTotalPrice(totalPrice);
                c.setTotalPrice(totalPrice);
                totalQuantity+=cl.getToolsQuantity();
                totalQuantity+=1;
                c.setToolsQuantity(totalQuantity);
                c.setEmpty(false);
                c.setToolOffer(product);
                c.setCreatedDate(new Date());
                //product.getCommandLines().add(c);
                // save the cart
                clr.save(c);

        }

    }


//-----------------------------------------LIST CART-----------------------------------

    @Override
    public CartDto listCartItems(Integer idUser) {

    User user= userRepository.findById(idUser).get();
    List<CommandLine> cartList = clr.findAllByUserOrderByCreatedDateDesc(user);

    List<CartItemDto> cartItems = new ArrayList<>();
    double totalCost = 0;
    for (CommandLine cart: cartList) {
        CartItemDto cartItemDto = new CartItemDto(cart);
        totalCost += cartItemDto.getQuantity() * cart.getToolOffer().getPrice();
        cartItems.add(cartItemDto);
    }

    CartDto cartDto = new CartDto();
    cartDto.setTotalCost(totalCost);
    cartDto.setCartItems(cartItems);
    return  cartDto;
}

//-----------------------------------------DELETE CART-----------------------------------

    @Override
    public void deleteCartItem(long cartItemId, Integer iduser) {
        // the item id belongs to user

        CommandLine cart = clr.findById(cartItemId).get();

        if (cart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }

        CommandLine c = cart;

        if (c.getUser().getIdMembre() != iduser) {
            throw  new CustomException("cart item does not belong to user: " +cartItemId);
        }

        clr.delete(cart);


    }

}

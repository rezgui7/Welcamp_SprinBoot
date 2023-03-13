package tn.esprit.welcamp.services;

//import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.welcamp.dto.CheckOut.CheckOutToolsDto;
import tn.esprit.welcamp.dto.cart.CartDto;
import tn.esprit.welcamp.dto.cart.CartItemDto;
import tn.esprit.welcamp.entities.Command;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.repositories.CommandLineRepository;
import tn.esprit.welcamp.repositories.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service //ou @Component
@Transactional
public class OrderService implements IOrderService{
    @Autowired
    OrderRepository or;
    @Autowired
    CommandLineRepository clr;
    @Autowired
    CommandLineService commandLineService;

    @Autowired
    MailService mailService;

    @Override
    public Command addOrder(Command o){return or.save(o);}



    @Override
    public List<Command> displayOrders(){ return (List<Command>) or.findAll();}


    @Override
    public Command displayOrder(long idOrder){return or.findById(idOrder).get();}

    @Override
    public Command modifieOrder(Command o){ return or.save(o); }

    @Override
    public void deleteOrder(Command o){or.delete(o);}


    @Override
    public CommandLine addAndAssignOfferToCommandline(CommandLine c, long idOffer){
        Command o=or.findById(idOffer).get();
        c=clr.save(c);
        or.save(o);

        o.getCommandLines().add(c);

        //tl.setCommandLines((List<CommandLine>) c);
        or.save(o);
        return c;
    }




    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    public Session createSession(List<CheckOutToolsDto> checkoutItemDtoList, Integer idUser) throws StripeException {
        // sucess and failure urls

        String successURL = baseURL + "payment/success";

        String failureURL = baseURL + "payment/failed";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        double c= commandLineService.listCartItems(idUser).getTotalCost();
        Command command= new Command();
        command.setCommandPassedAt(new Date());
        command.setTotal(c);
        addOrder(command);



        for (CheckOutToolsDto checkoutItemDto: checkoutItemDtoList) {
            sessionItemList.add(createSessionLineItem(checkoutItemDto,idUser));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failureURL)
                .addAllLineItem(sessionItemList)
                .setSuccessUrl(successURL)
                .build();

        mailService.sendMail("rezguiy67@gmail.com","payment success","a payment successfully done");

        return Session.create(params);


    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckOutToolsDto checkoutItemDto,Integer idUser) {
        int q=commandLineService.listCartItems(idUser).getCartItems().size();

        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItemDto,idUser))
                .setQuantity(Long.parseLong(String.valueOf(q)))
                .build();

    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CheckOutToolsDto checkoutItemDto,Integer idUser) {
        double c = commandLineService.listCartItems(idUser).getTotalCost();
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long)(c*100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getCardNumber())
                                .build()
                ).build();
    }
}

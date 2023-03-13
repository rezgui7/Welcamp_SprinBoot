package tn.esprit.welcamp.services;

import tn.esprit.welcamp.dto.cart.CartDto;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.ToolOffer;

import java.util.List;

public interface ICommandLineService {
    CommandLine addCommandLine(CommandLine cl);

    List<CommandLine> displayCommandLine();

    CommandLine displayCommandLines(long idCommandLine);

    CommandLine modifieCommandLine(CommandLine cl);

    void deleteCommandLine(CommandLine cl);

    //    @Override
    //    public ToolOffer addAndAssignToolOfferToCommandline(ToolOffer t, long idCommandline){
    //        CommandLine c = clr.findById(idCommandline).get();
    //        t=tor.save(t);
    //        c.setToolOffer(t);
    //        clr.save(c);
    //
    //
    //
    //    }
    void addToCart(CommandLine c,long idTool,long idUser);

    CartDto listCartItems(Integer idUser);

    void deleteCartItem(long cartItemId, Integer iduser);
}

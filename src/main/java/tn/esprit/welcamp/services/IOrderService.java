package tn.esprit.welcamp.services;

import tn.esprit.welcamp.entities.Command;
import tn.esprit.welcamp.entities.CommandLine;

import java.util.List;

public interface IOrderService {
    Command addOrder(Command o);

    List<Command> displayOrders();

    Command displayOrder(long idOrder);

    Command modifieOrder(Command o);

    void deleteOrder(Command o);

    CommandLine addAndAssignOfferToCommandline(CommandLine c, long idOffer);
}

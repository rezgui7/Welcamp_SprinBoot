package tn.esprit.welcamp.services;

import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.ToolOffer;

import java.util.List;

public interface IToolOfferService {
    ToolOffer addToolOffer (ToolOffer to);

    List<ToolOffer> displayToolOffers();

    ToolOffer displayToolOffer(long idToolOffer);

    ToolOffer modifieToolOffer(ToolOffer to);

    void deleteToolOffer(ToolOffer to);

    List<ToolOffer> searchToolOfferByName(String toolName);


    List<ToolOffer> searchMultiCritere(String toolName, String brand, double price, String categoryTools);

    List<ToolOffer> searchByCategoryTools(String category);

    ToolOffer assignCategoryToOffer(Integer idCategorie, ToolOffer to);

//    CommandLine addAndAssignToolOfferToCommandline(CommandLine c, long idToolOffer);
}

package tn.esprit.welcamp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.ToolOffer;
import tn.esprit.welcamp.exceptions.ProductNotExistsException;
import tn.esprit.welcamp.repositories.CategoryToolsRepository;
import tn.esprit.welcamp.repositories.CommandLineRepository;
import tn.esprit.welcamp.repositories.ToolOfferRepository;

import java.util.List;
import java.util.Optional;

@Service //ou @Component
@Transactional
public class ToolOfferService implements IToolOfferService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolOffer.class);

    @Autowired
    ToolOfferRepository tor;

    @Autowired
    CommandLineRepository clr;

    @Autowired
    CategoryToolsRepository ctr;



//----------------------CRUD--------------------------------------------------------------------------------------

    @Override
    public ToolOffer addToolOffer(ToolOffer to){ return tor.save(to); }

    @Override
    public List<ToolOffer> displayToolOffers(){ return (List<ToolOffer>) tor.findAll();}

    @Override
    public ToolOffer displayToolOffer(long idToolOffer){return tor.findById(idToolOffer).get();}

    @Override
    public ToolOffer modifieToolOffer(ToolOffer to){ return tor.save(to); }

    @Override
    public void deleteToolOffer(ToolOffer to){tor.delete(to);}

    //----------------------search--------------------------------------------------------------------------------------


    @Override
    public List<ToolOffer> searchToolOfferByName(String toolName){
        return tor.findByName(toolName);
    }

    @Override
    public List<ToolOffer> searchMultiCritere(String toolName, String brand, double price, String categoryTools){
        return tor.multiFind(toolName,brand,price,categoryTools);
    }

    @Override
    public List<ToolOffer> searchByCategoryTools(String category){
        return tor.searchByCategoryTools(category);
    }


//----------------------assign--------------------------------------------------------------------------------------


    @Override
    public ToolOffer assignCategoryToOffer(Integer idCategorie, ToolOffer to){
        CategoryTools c = ctr.findById(Long.valueOf(idCategorie)).get();
        //ToolOffer t = (ToolOffer) tor.findAll();
        to.setCategoryTools(c);
        return tor.save(to);
    }



    public ToolOffer findById(long productId) throws ProductNotExistsException {
        Optional<ToolOffer> optionalProduct = tor.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("tool offer id is invalid: " + productId);
        }
        return optionalProduct.get();
    }

}

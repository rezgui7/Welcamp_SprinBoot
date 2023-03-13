package tn.esprit.welcamp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.entities.CommandLine;
import tn.esprit.welcamp.entities.Promotion;
import tn.esprit.welcamp.entities.ToolOffer;
import tn.esprit.welcamp.repositories.CategoryToolsRepository;
import tn.esprit.welcamp.repositories.CommandLineRepository;
import tn.esprit.welcamp.repositories.PromotionRepository;
import tn.esprit.welcamp.repositories.ToolOfferRepository;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
@Transactional
public class PromotionService implements IPromotionService {
    @Autowired
    PromotionRepository pr;
    @Autowired
    CategoryToolsRepository ctr;

    @Autowired
    ToolOfferRepository tor;

    @Autowired
    CategoryToolsRepository categoryToolsRepository;

    @Autowired
    CommandLineRepository commandLineRepository;
    @Autowired
    private TaskScheduler taskScheduler;





    @Override
    public Promotion addPromotion(Promotion p){return pr.save(p);}


    @Override
    public List<Promotion> displayPromotions(){ return (List<Promotion>) pr.findAll();}

    @Override
    public Promotion displayPromotion(long idPromotion){return pr.findById(idPromotion).get();}

    @Override
    public Promotion modifiePromotion(Promotion p){ return pr.save(p); }

    @Override
    public void deletePromotion(Promotion p){pr.delete(p);}

    @Override
    public void assignPromotionToCategory(long idCategory, Promotion p){
        CategoryTools c = ctr.findById(Long.valueOf(idCategory)).get();
        c.setPromotion(p);
        ctr.save(c);
    }

    @Override
    public void assignPromotionToTool(long idTool, Promotion p){
        ToolOffer t = tor.findById(Long.valueOf(idTool)).get();
        t.setPromotion(p);
        tor.save(t);
    }

    public String applyDiscount(float discountPourcentage, long idToolOffer){
        double pd ;
        ToolOffer t = tor.findById(idToolOffer).get();
        double prix = t.getPrice();
        if ((discountPourcentage<1.0) && (discountPourcentage>0.0)){
            pd=prix-prix*discountPourcentage;
            t.setPrice(pd);
            t.setInPromotion(true);
            return "discount applied successfully";
        }
        return "discount should be between 0 and 1";
    }
    public String applyDiscount2(float discountPourcentage){
        double pd ;
        List<ToolOffer> t = (List<ToolOffer>) tor.findAll();
        if ((discountPourcentage<1.0) && (discountPourcentage>0.0)){

            for (ToolOffer to: t) {
                double prix = to.getPrice();
                pd=prix-prix*discountPourcentage;
                to.setPrice(pd);
                to.setInPromotion(true);
            }

            return "discount applied successfully";
        }
        return "discount should be between 0 and 1";
    }

    public double disableDiscount(double p , float d){
        double p1 = p/(1-d);
        return p1;
    }


    @Override
    public String applyPromotion(Promotion p, long idTool){
        assignPromotionToTool(idTool,p);
        ToolOffer t= tor.findById(idTool).get();
        float dp = (float) p.getAmount();
        applyDiscount(dp,idTool);
        double pr=t.getPrice();

        taskScheduler.schedule(() -> {
            double p1=disableDiscount(pr,dp);
            t.setPrice(p1);
            t.setInPromotion(false);
            t.setPromotion(null);
            tor.save(t);
            System.out.println("Task executed at " + new Date() );
        }, p.getExpires());

        return "promotion applied successfully and expire at " +p.getExpires() ;
    }

    @Override
    public String applyPromotionOnCategoty(Promotion p, long idCategory){
        CategoryTools c = ctr.findById(idCategory).get();
        List<ToolOffer> toolOffers = (List<ToolOffer>) tor.findAll();
        for (ToolOffer t:toolOffers) {
            if (t.getCategoryTools().getIdCategoryTools()==idCategory){
                applyPromotion(p,t.getIdToolOffer());
                //assignPromotionToCategory(idCategory,p);
            }

        }
        return "Promotion Applied successfully";
    }

    public String applyPromotionOnAllCategories(Promotion p){
        List<ToolOffer> toolOffers = (List<ToolOffer>) tor.findAll();
        for (ToolOffer t:toolOffers) {
            applyPromotion(p,t.getIdToolOffer());
        }
        return "Promotion Applied successfully";

    }


    public void loyaltyPoints(long idUser){
        int i=1;
        List<CommandLine> c = (List<CommandLine>) commandLineRepository.findAll();
//        Promotion p = new Promotion();
//        p.setAmount(0.2);
//        p.setExpires(new Date());
        for (CommandLine cl:c) {
            if (idUser==cl.getUser().getIdMembre()){
                i++;
            }
            System.out.println(i);
        }
        System.out.println(i);
        if (i>=3){
//            applyPromotionOnAllCategories(p);
              applyDiscount2((float) 0.2);
        }

    }



}

package tn.esprit.welcamp.services;

import tn.esprit.welcamp.entities.Promotion;

import java.util.List;

public interface IPromotionService {
    Promotion addPromotion(Promotion p);

    List<Promotion> displayPromotions();

    Promotion displayPromotion(long idPromotion);

    Promotion modifiePromotion(Promotion p);

    void deletePromotion(Promotion p);

    void assignPromotionToCategory(long idCategory, Promotion p);

    void assignPromotionToTool(long idTool, Promotion p);

    String applyPromotion(Promotion p, long idTool);

    String applyPromotionOnCategoty(Promotion p, long idCategory);
}

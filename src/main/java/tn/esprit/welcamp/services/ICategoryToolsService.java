package tn.esprit.welcamp.services;

import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.entities.ToolOffer;

import java.util.List;

public interface ICategoryToolsService {
    CategoryTools addCategoryTools(CategoryTools ct);

    List<CategoryTools> displayCategoryTools();

    CategoryTools displayCategoryTools(long idCategoryTools);

    CategoryTools modifieCategoryTools(CategoryTools ct);

    void deleteCategoryTools(CategoryTools ct);

}

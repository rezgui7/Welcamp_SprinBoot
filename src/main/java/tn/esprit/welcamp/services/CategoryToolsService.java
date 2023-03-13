package tn.esprit.welcamp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.welcamp.entities.CategoryTools;
import tn.esprit.welcamp.entities.ToolOffer;
import tn.esprit.welcamp.repositories.CategoryToolsRepository;
import tn.esprit.welcamp.repositories.ToolOfferRepository;

import java.util.List;

@Service //ou @Component
@Transactional
public class CategoryToolsService implements ICategoryToolsService {
    @Autowired
    CategoryToolsRepository ctr;

    @Autowired
    ToolOfferRepository tor;

    @Override
    public CategoryTools addCategoryTools(CategoryTools ct){return ctr.save(ct);}


    @Override
    public List<CategoryTools> displayCategoryTools(){ return (List<CategoryTools>) ctr.findAll();}


    @Override
    public CategoryTools displayCategoryTools(long idCategoryTools){return ctr.findById(idCategoryTools).get();}

    @Override
    public CategoryTools modifieCategoryTools(CategoryTools ct){ return ctr.save(ct); }

    @Override
    public void deleteCategoryTools(CategoryTools ct){ctr.delete(ct);}



}

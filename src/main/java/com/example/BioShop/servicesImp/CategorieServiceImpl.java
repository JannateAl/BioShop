package com.example.BioShop.servicesImp;

import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.CategorieRepository;
import com.example.BioShop.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public List<Produit> findProduitbyCategorieNom(String nom) {
        return categorieRepository.findCategorieByNom(nom).getProduits();
    }
}

package com.example.BioShop.services;

import com.example.BioShop.entities.Produit;

import java.util.List;

public interface CategorieService {
    public List<Produit> findProduitbyCategorieNom(String nom);
}

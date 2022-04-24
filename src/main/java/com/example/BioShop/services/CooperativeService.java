package com.example.BioShop.services;

import com.example.BioShop.entities.Cooperative;
import com.example.BioShop.entities.Produit;

import java.util.List;

public interface CooperativeService {

    public List<Cooperative> listeCooperatives();
    public void creerCooperative(Cooperative cooperative);
    public List<Cooperative> findCooperativeByName(String nomCooperative);
    public void deleteCooperative(Long id);
    public List<Produit> showProduitsByCooperative(int idcooperative);
    public List<Cooperative> findCooperativeBySecteurActivite(String nomSecteur);
    public List<Cooperative> findCooperativeByRegion(String nom);

}

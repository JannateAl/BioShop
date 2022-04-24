package com.example.BioShop.services;

import com.example.BioShop.entities.Produit;

import java.util.List;

public interface ProduitService {

    public List<Produit> listeProduits();
    public void creerProduit(Produit produit, List<String> matierePremiere);
    public Produit getProduitById(int id);
    public void deleteProduit(int id);
    public List<Produit> findProduitByName(String nomProduit);
    public List<Produit> getProduitByMatierePremiere(List<String> matieresPremieres);
    public List<Produit> getProduitbyCooperativeAndCategorie(String categorieNom,String cooperativeNom);
    public List<Produit> getProduitsByRegion(String nom);
}

package com.example.BioShop.servicesImp;

import com.example.BioShop.entities.Composant;
import com.example.BioShop.entities.Cooperative;
import com.example.BioShop.entities.MatierePremiere;
import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.ComposantRepository;
import com.example.BioShop.repositories.CooperativeRepository;
import com.example.BioShop.repositories.MatierePremiereRepository;
import com.example.BioShop.repositories.ProduitRepository;
import com.example.BioShop.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImp implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CooperativeRepository cooperativeRepository;

    @Autowired
    private MatierePremiereRepository matierePremiereRepository;

    @Autowired
    private ComposantRepository composantRepository;

    @Override
    public List<Produit> listeProduits(){

        return produitRepository.findAll();
    }

    @Override
    public void creerProduit(Produit produit, List<String> matierePremiere){
        produitRepository.save(produit);
        for(String matiere : matierePremiere) {
            MatierePremiere matiere1 = matierePremiereRepository.findMatierePremiereByNom(matiere);
            Composant composant = new Composant();
            composant.setProduit(produit);
            composant.setMatierePremiere(matiere1);
            composantRepository.save(composant);
            produit.getComposantList().add(composant);
        }
        this.produitRepository.save(produit);
    }

    @Override
    public Produit getProduitById(int id) {
        Optional<Produit> optional = produitRepository.findById(id);
        Produit produit = null;
        if(optional.isPresent()) {
            produit = optional.get();
        }
        else {
            throw new RuntimeException("Produit noun trouve avec id=" + id);
        }
        return produit;
    }

    @Override
    public List<Produit> getProduitByMatierePremiere(List<String> matieresPremieres){

        List<Produit> produits = new ArrayList<Produit>();
        for(String matiere : matieresPremieres){
            MatierePremiere m = matierePremiereRepository.findMatierePremiereByNom(matiere);
            List<Integer> IDproducts = composantRepository.findProduitByMatiere(m.getId());
            for(int p : IDproducts){
                Produit produit = produitRepository.findById(p).get();
                if(!produits.contains(produit)){
                    produits.add(produit);
                }
            }
        }
        return produits;
    }

    @Override
    public List<Produit> getProduitbyCooperativeAndCategorie(String categorieNom, String cooperativeNom) {
        return produitRepository.findProduitByCategorieNomAndCooperativeNom(categorieNom,cooperativeNom);
    }

    @Override
    public List<Produit> getProduitsByRegion(String nom) {

        List<Cooperative> cooperatives = cooperativeRepository.findCooperativeByRegionNom(nom);

        List<Produit> produits = new ArrayList<>();

        for (Cooperative cooperative:cooperatives) {
            List<Produit> temp = produitRepository.findProduitByCooperative(cooperative);
            produits.addAll(temp);
        }

        return produits;
    }

    @Override
    public void deleteProduit(int id) {

        produitRepository.deleteById(id);
    }

    @Override
    public List<Produit> findProduitByName(String nomProduit){
        List<Produit> produits = produitRepository.findProduitByNom(nomProduit);
        if(produits.isEmpty()) {
            System.out.println("liste vide");
        }
        return produitRepository.findProduitByNom(nomProduit);
    }

}

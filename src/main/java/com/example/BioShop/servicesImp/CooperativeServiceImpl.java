package com.example.BioShop.servicesImp;

import com.example.BioShop.entities.Cooperative;
import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.CooperativeRepository;
import com.example.BioShop.repositories.ProduitRepository;
import com.example.BioShop.repositories.SecteurActiviteRepository;
import com.example.BioShop.services.CooperativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CooperativeServiceImpl implements CooperativeService {

    @Autowired
    private CooperativeRepository cooperativeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private SecteurActiviteRepository secteurActiviteRepository;

    @Override
    public List<Cooperative> listeCooperatives() {
        return cooperativeRepository.findAll();
    }

    @Override
    public void creerCooperative(Cooperative cooperative) {

        cooperativeRepository.save(cooperative);
    }

    @Override
    public List<Cooperative> findCooperativeByName(String nomCooperative) {
        List<Cooperative> cooperatives = cooperativeRepository.findCooperativeByName(nomCooperative);
        return cooperatives;
    }

    @Override
    public void deleteCooperative(Long id) {

        Cooperative cooperative = cooperativeRepository.findById(id).get();
        cooperativeRepository.delete(cooperative);
    }
    @Override
    public List<Produit> showProduitsByCooperative(int idcooperative) {

        List<Produit> produits = new ArrayList<Produit>();
        List<Integer> IDproduits = cooperativeRepository.findProduitByCooperative(idcooperative);
        for(int p : IDproduits){
            Produit produit = produitRepository.findById(p).get();
            produits.add(produit);
        }
        return produits;
    }

    @Override
    public List<Cooperative> findCooperativeBySecteurActivite(String nomSecteur) {

        int idSecteur = secteurActiviteRepository.findSecteurActiviteByNom(nomSecteur);
        return cooperativeRepository.findCooperativeBySecteurActivite(idSecteur);
    }

    @Override
    public List<Cooperative> findCooperativeByRegion(String nom) {
        return cooperativeRepository.findCooperativeByRegionNom(nom);
    }
}

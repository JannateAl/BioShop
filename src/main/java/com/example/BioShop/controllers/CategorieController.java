package com.example.BioShop.controllers;

import com.example.BioShop.entities.Categorie;
import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.CategorieRepository;
import com.example.BioShop.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/categories")
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @PostMapping("/createCategorie")
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @PostMapping("/{id}/delete")
    public void deleteCategorie(@PathVariable("id") Long id) {
        categorieRepository.deleteById(id);
    }

    @GetMapping(path = "/categorie/{categorieNom}/produits")
    public List<Produit> getProduitsParCategorie(@PathVariable("categorieNom") String nom ){
        return categorieService.findProduitbyCategorieNom(nom);
    }
}

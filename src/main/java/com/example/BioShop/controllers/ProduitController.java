package com.example.BioShop.controllers;

import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/produits")
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
       return produitRepository.save(produit);
    }


}

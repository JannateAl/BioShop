package com.example.BioShop.controllers;

import com.example.BioShop.entities.Composant;
import com.example.BioShop.entities.MatierePremiere;
import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.ComposantRepository;
import com.example.BioShop.repositories.MatierePremiereRepository;
import com.example.BioShop.repositories.ProduitRepository;
import com.example.BioShop.services.MatierePremiereService;
import com.example.BioShop.services.ProduitService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private MatierePremiereService matierePremiereService;

    @Autowired
    private ComposantRepository composantRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private MatierePremiereRepository matierePremiereRepository;

    @GetMapping("/produits")
    public String listeProduits(Model model) {
        List<Produit> produits = produitService.listeProduits();
        List<MatierePremiere> matieresPremieres = matierePremiereService.listeMatierePremiere();
        model.addAttribute("matieresPremieres" , matieresPremieres);
        model.addAttribute("produits", produits);
        return "indexProduit";
    }

    @GetMapping("/ajouterProduit")
    public String ajouterProduit(Model model) {
        Produit produit = new Produit();
        List<MatierePremiere> matieresPremieres = matierePremiereService.listeMatierePremiere();
        model.addAttribute("produit", produit);
        model.addAttribute("matieresPremieres" , matieresPremieres);
        return "creerProduit";
    }

    @PostMapping("/saveProduit")
    public String saveProduit(@ModelAttribute("produit") Produit produit, @RequestParam(value = "matierePremiere") List<String> matieresPremieres) {

        produitService.creerProduit(produit, matieresPremieres);
        return "redirect:/produits";
    }

    @GetMapping("/updateProduit/{id}")
    public String updateproduit(@PathVariable(value = "id") int id, Model model){

        Produit produit = produitService.getProduitById(id);
        List<MatierePremiere> matieresPremieres = matierePremiereService.listeMatierePremiere();
        model.addAttribute("matieresPremieres" , matieresPremieres);
        model.addAttribute("produit",produit);
        return "modifierProduit";
    }

    @GetMapping("/deleteProduit/{id}")
    public String deleteProduit(@PathVariable(value = "id") int id){

        produitService.deleteProduit(id);
        return "redirect:/produits";
    }

    @PostMapping("/searchProduitByName")
    public String searchProduitByName(@RequestBody String nomProduit, Model model){

        List<Produit> produits = produitService.findProduitByName(nomProduit);
        System.out.println(produits.get(1).getNom());
        model.addAttribute("produits", produits);
        return "indexProduit";
    }

    @PostMapping("/searchProduitByComposant")
        public String searchProduitByComposant(Model model, @RequestParam(value = "matierePremiere") List<String> matieresPremieres){

        List<Produit> produits = produitService.getProduitByMatierePremiere(matieresPremieres);
        model.addAttribute("produits", produits);
        return "indexProduit";
    }



}

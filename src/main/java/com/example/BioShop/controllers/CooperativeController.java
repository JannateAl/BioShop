package com.example.BioShop.controllers;

import com.example.BioShop.entities.Cooperative;
import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.CooperativeRepository;
import com.example.BioShop.repositories.ProduitRepository;
import com.example.BioShop.services.CooperativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CooperativeController {

    @Autowired
    private CooperativeService cooperativeService;

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/cooperatives")
    public String getAllCooperatives(Model model) {

        List<Cooperative> cooperatives = cooperativeService.listeCooperatives();
        model.addAttribute("cooperatives", cooperatives);
        return "indexCooperative";
    }

    @PostMapping("/creerCooperative")
    public String createCooperative(@RequestBody Cooperative cooperative) {

        cooperativeService.creerCooperative(cooperative);
        return "indexCooperative";
    }

    @GetMapping("/showProduits/{id}")
    public String showProduits(Model model, @PathVariable(value = "id") int id){

        List<Produit> produits = cooperativeService.showProduitsByCooperative(id);
        model.addAttribute("produits", produits);
        return "indexProduit";
    }

    @GetMapping("/deleteCooperative/{id}")
    public String deleteCooperative(@PathVariable(value = "id") Long id){

        cooperativeService.deleteCooperative(id);
        return "redirect:/cooperatives";
    }

    @PostMapping("/searchCooperativeByName")
    public String searchCooperativeByName(Model model, @RequestParam(value = "nomCooperative") String nomCooperative){

        List<Cooperative> cooperatives = cooperativeService.findCooperativeByName(nomCooperative);
        model.addAttribute("cooperatives", cooperatives);
        return "indexCooperative";
    }

    @PostMapping("/searchCooperativeBySecteur")
    public String searchCooperativeBySecteur(Model model, @RequestParam(value = "nomSecteur") String nomSecteur){

       List<Cooperative> cooperatives = cooperativeService.findCooperativeBySecteurActivite(nomSecteur);
       model.addAttribute("cooperatives", cooperatives);
       return "indexCooperative";
    }

}

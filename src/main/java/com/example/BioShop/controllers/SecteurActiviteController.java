package com.example.BioShop.controllers;

import com.example.BioShop.entities.Region;
import com.example.BioShop.entities.SecteurActivite;
import com.example.BioShop.repositories.SecteurActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/secteurs")
public class SecteurActiviteController {

    @Autowired
    private SecteurActiviteRepository secteurActiviteRepository;

    @GetMapping
    public List<SecteurActivite> getAllSecteurs() {
        return secteurActiviteRepository.findAll();
    }

    @PostMapping
    public SecteurActivite createSecteur(@RequestBody SecteurActivite secteurActivite) {
        return secteurActiviteRepository.save(secteurActivite);
    }
}

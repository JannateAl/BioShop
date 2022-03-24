package com.example.BioShop.controllers;

import com.example.BioShop.entities.MatierePremiere;
import com.example.BioShop.repositories.MatierePremiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/matieres")
public class MatierePremiereController {

    @Autowired
    private MatierePremiereRepository matierePremiereRepository;

    @GetMapping
    public List<MatierePremiere> getAllMatieresPremieres() {
        return matierePremiereRepository.findAll();
    }

    @PostMapping
    public MatierePremiere createMatierePremiere(@RequestBody MatierePremiere matierePremiere) {
        return matierePremiereRepository.save(matierePremiere);
    }


}

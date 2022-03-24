package com.example.BioShop.controllers;

import com.example.BioShop.entities.Cooperative;
import com.example.BioShop.repositories.CooperativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cooperatives")
public class CooperativeController {

    @Autowired
    private CooperativeRepository cooperativeRepository;

    @GetMapping
    public List<Cooperative> getAllCooperatives() {
        return cooperativeRepository.findAll();
    }

    @PostMapping
    public Cooperative createCooperative(@RequestBody Cooperative cooperative) {
        return cooperativeRepository.save(cooperative);
    }
}

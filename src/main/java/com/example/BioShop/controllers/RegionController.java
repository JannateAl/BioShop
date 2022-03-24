package com.example.BioShop.controllers;

import com.example.BioShop.entities.Region;
import com.example.BioShop.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/regions")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @PostMapping
    public Region createRegion(@RequestBody Region region) {
        return regionRepository.save(region);
    }
}

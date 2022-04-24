package com.example.BioShop.services;

import com.example.BioShop.entities.Region;

import java.util.List;

public interface RegionService {

    public List<Region> getAllRegions();
    public Region creerRegion(Region region);
}

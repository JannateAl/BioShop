package com.example.BioShop.repositories;
import com.example.BioShop.entities.Produit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
}

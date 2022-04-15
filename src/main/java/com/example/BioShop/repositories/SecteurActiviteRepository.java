package com.example.BioShop.repositories;

import com.example.BioShop.entities.Produit;
import com.example.BioShop.entities.SecteurActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecteurActiviteRepository extends JpaRepository<SecteurActivite, Long> {

    @Query(value = "select id from secteur_activite  where nom LIKE :nomSecteur", nativeQuery = true)
    int findSecteurActiviteByNom(@Param(value = "nomSecteur") String nomSecteur);
}

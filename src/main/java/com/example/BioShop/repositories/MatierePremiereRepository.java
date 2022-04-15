package com.example.BioShop.repositories;

import com.example.BioShop.entities.MatierePremiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatierePremiereRepository extends JpaRepository<MatierePremiere, Long> {

    @Query(value = "select * from matiere_premiere  where nom = :matierePremiere", nativeQuery = true)
    MatierePremiere findMatierePremiereByNom(String matierePremiere);
}

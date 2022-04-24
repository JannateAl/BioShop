package com.example.BioShop.repositories;

import com.example.BioShop.entities.Composant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComposantRepository extends JpaRepository<Composant, Integer> {

   /* @Query(value = "select id_produit from composant  where id_matiere_premiere = :idMatiere ", nativeQuery = true)
    int findComposant(int idMatiere); */

    @Query(value = "select id_produit from composant  where id_matiere_premiere = :idMatiere ", nativeQuery = true)
    List<Integer> findProduitByMatiere(int idMatiere);
}

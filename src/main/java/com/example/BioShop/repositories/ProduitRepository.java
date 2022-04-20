package com.example.BioShop.repositories;
import com.example.BioShop.entities.Cooperative;
import com.example.BioShop.entities.MatierePremiere;
import com.example.BioShop.entities.Produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

    @Query(value = "select * from produit  where nom LIKE :nomProduit", nativeQuery = true)
    List<Produit> findProduitByNom(@Param(value = "nomProduit") String nomProduit);

    public List<Produit> findProduitByCategorieNomAndCooperativeNom(String categorieNom, String cooperativeNom);

    public List<Produit> findProduitByCooperative(Cooperative cooperative);
}

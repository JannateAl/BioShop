package com.example.BioShop.repositories;
import com.example.BioShop.entities.Cooperative;
import com.example.BioShop.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CooperativeRepository extends JpaRepository<Cooperative, Long> {

    @Query(value = "select id from produit  where idcooperative = :idcooperative", nativeQuery = true)
    List<Integer> findProduitByCooperative(@Param(value = "idcooperative") int idcooperative);

    @Query(value = "select * from cooperative  where nom like :nomCooperative", nativeQuery = true)
    List<Cooperative> findCooperativeByName(@Param(value = "nomCooperative") String nomCooperative);

    @Query(value = "select * from cooperative  where idsecteur = :idSecteur", nativeQuery = true)
    List<Cooperative> findCooperativeBySecteurActivite(@Param(value = "idSecteur") int idSecteur);

    public List<Cooperative> findCooperativeByRegionNom(String nom);

}




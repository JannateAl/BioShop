package com.example.BioShop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Categorie {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

}

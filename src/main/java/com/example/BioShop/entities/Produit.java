package com.example.BioShop.entities;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Produit {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;
    private String nom;
    private int quantite;
    private double prix;

    @OneToMany(mappedBy = "produit", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Composant> composantList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idcooperative")
    private Cooperative cooperative;
}

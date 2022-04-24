package com.example.BioShop.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Composant {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int code;
    private double pourcentage;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "idMatierePremiere")
    private MatierePremiere matierePremiere;





}

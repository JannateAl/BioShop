package com.example.BioShop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private int id;
    private String nom;
    private int quantite;
    private double prix;
    private String unite;
    private double stock;
    private String photo;
    @ManyToOne
    @JoinColumn(name="id_categorie")
    private Categorie categorie;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "matiere_produit",
            joinColumns = @JoinColumn(name = "id_matiere", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_produit",
                    referencedColumnName = "id"))
    private List<MatierePremiere> matieres;

    @ManyToOne
    @JoinColumn(name="id_cooperative")
    private Cooperative cooperative;

    public Produit(int id, String nom, int quantite, double prix, String unite, double stock, String photo) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.unite = unite;
        this.stock = stock;
        this.photo = photo;
    }
}

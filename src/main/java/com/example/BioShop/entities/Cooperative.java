package com.example.BioShop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Cooperative {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "id_region")
    private Region region;

    @OneToMany(mappedBy = "cooperative")
    private List<Produit> produits;

    @ManyToMany
    @JoinTable(name = "secteur_cooperative",
            joinColumns = @JoinColumn(name = "id_secteur", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_cooperative",
                    referencedColumnName = "id"))
    private List<SecteurActivite> secteurs;
}

package com.example.BioShop.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class MatierePremiere {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;
    private String nom;

    @OneToMany(mappedBy = "matierePremiere")
    private List<Composant> composantList;
}

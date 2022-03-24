package com.example.BioShop.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Region {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "region")
    private List<Cooperative> cooperatives;
}

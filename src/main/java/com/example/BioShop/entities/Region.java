package com.example.BioShop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int id;
    private String nom;
    @OneToMany(mappedBy = "region",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cooperative> cooperatives;
}

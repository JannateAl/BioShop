package com.example.BioShop;

import com.example.BioShop.entities.Produit;
import com.example.BioShop.repositories.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class TestClass {

    @Bean
    CommandLineRunner commandLineRunner(ProduitRepository produitRepository) {
        return args -> {
            Produit prod1 = new Produit(
                    1,
                    "Argan",
                    50,
                    300);
            Produit prod2 = new Produit(
                    2,
                    "Olive oil",
                    30,
                    250);
            produitRepository.save(prod1);
            produitRepository.save(prod2);
        };
    }
}

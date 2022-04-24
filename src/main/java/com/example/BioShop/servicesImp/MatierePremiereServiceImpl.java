package com.example.BioShop.servicesImp;

import com.example.BioShop.entities.MatierePremiere;
import com.example.BioShop.repositories.MatierePremiereRepository;
import com.example.BioShop.services.MatierePremiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatierePremiereServiceImpl implements MatierePremiereService {

    @Autowired
    MatierePremiereRepository matierePremiereRepository;

    @Override
    public List<MatierePremiere> listeMatierePremiere() {

        return matierePremiereRepository.findAll() ;
    }
}

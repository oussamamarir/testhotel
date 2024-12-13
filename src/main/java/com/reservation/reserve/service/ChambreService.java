package com.reservation.reserve.service;

import com.reservation.reserve.entite.Chambre;
import com.reservation.reserve.exception.ResourceNotFoundException;
import com.reservation.reserve.repositorie.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Service pour Chambre
@Service
public class ChambreService {
    @Autowired
    private ChambreRepository chambreRepository;

    public Chambre createChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    public Chambre getChambreById(Long id) {
        return chambreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chambre not found"));
    }

    public Chambre updateChambre(Long id, Chambre chambreDetails) {
        Chambre chambre = getChambreById(id);
        chambre.setType(chambreDetails.getType());
        chambre.setPrix(chambreDetails.getPrix());
        chambre.setDisponible(chambreDetails.isDisponible());
        return chambreRepository.save(chambre);
    }

    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }
}



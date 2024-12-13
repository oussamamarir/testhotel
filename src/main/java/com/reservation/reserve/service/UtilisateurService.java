package com.reservation.reserve.service;
import com.reservation.reserve.entite.Utilisateur;
import com.reservation.reserve.repositorie.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reservation.reserve.exception.ResourceNotFoundException;


@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found"));
    }

    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = getUtilisateurById(id);
        utilisateur.setNomUtilisateur(utilisateurDetails.getNomUtilisateur());
        utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
        utilisateur.setRole(utilisateurDetails.getRole());
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
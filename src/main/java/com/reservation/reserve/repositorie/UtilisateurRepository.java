package com.reservation.reserve.repositorie;

import com.reservation.reserve.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}

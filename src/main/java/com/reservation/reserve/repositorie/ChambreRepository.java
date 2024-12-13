package com.reservation.reserve.repositorie;

import com.reservation.reserve.entite.Chambre;
import com.reservation.reserve.entite.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

}

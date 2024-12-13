package com.reservation.reserve.repositorie;

import com.reservation.reserve.entite.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClientNom(String nom);
    Reservation findByReservationCode(String reservationCode); // Use String instead of long
}



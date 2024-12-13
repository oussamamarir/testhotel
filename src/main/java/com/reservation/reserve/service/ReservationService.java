package com.reservation.reserve.service;

import com.reservation.reserve.entite.Chambre;
import com.reservation.reserve.entite.Client;
import com.reservation.reserve.entite.Reservation;
import com.reservation.reserve.repositorie.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // Obtenir toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Obtenir les réservations par nom du client
    public List<Reservation> getReservationsByClientName(String clientName) {
        return reservationRepository.findByClientNom(clientName);
    }

    // Obtenir une réservation par code
    public Reservation getReservationByCode(String reservationCode) {
        return reservationRepository.findByReservationCode(reservationCode);
    }


    // Créer ou mettre à jour une réservation
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Créer une réservation avec des paramètres spécifiques
    public Reservation createReservation(Reservation reservation,Client clientId, Chambre chambreId, String preferences, LocalDate dateDebut, LocalDate dateFin) {
        reservation.setClient(clientId);
        reservation.setChambre(chambreId);
        reservation.setPreferences(preferences);
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        return reservationRepository.save(reservation);
    }

    // Supprimer une réservation par ID
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }


}

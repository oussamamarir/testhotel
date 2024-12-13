package com.reservation.reserve.graphql;

import com.reservation.reserve.entite.Reservation;
import com.reservation.reserve.service.ReservationService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservationMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private ReservationService reservationService;

    public Reservation createReservation(String preferences, String dateDebut, String dateFin) {
        Reservation reservation = new Reservation();
        reservation.setPreferences(preferences);
        reservation.setDateDebut(LocalDate.parse(dateDebut));
        reservation.setDateFin(LocalDate.parse(dateFin));
        return reservationService.saveReservation(reservation);
    }
}

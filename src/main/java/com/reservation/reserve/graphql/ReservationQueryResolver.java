package com.reservation.reserve.graphql;

import com.reservation.reserve.entite.Reservation;
import com.reservation.reserve.service.ReservationService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ReservationService reservationService;

    public Reservation getReservation(String reservationCode) {
        return reservationService.getReservationByCode(reservationCode);
    }
}

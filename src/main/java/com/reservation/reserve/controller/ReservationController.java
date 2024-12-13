package com.reservation.reserve.controller;

import com.reservation.reserve.entite.Reservation;
import com.reservation.reserve.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/by-client-name/{name}")
    public ResponseEntity<List<Reservation>> getReservationsByClientName(@PathVariable String name) {
        return ResponseEntity.ok(reservationService.getReservationsByClientName(name));
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<Reservation> getReservationByCode(@PathVariable String code) {
        return ResponseEntity.ok(reservationService.getReservationByCode(String.valueOf(Long.parseLong(code))));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}

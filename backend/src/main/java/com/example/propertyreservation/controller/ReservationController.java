package com.example.propertyreservation.controller;

import com.example.propertyreservation.model.Client;
import com.example.propertyreservation.model.Reservation;
import com.example.propertyreservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/property/{propertyId}")
    public List<Reservation> getReservationsByProperty(@PathVariable Long propertyId) {
        return reservationService.getReservationsByProperty(propertyId);
    }

    @GetMapping("/client/{clientId}")
    public List<Reservation> getReservationsByClient(@PathVariable Long clientId) {
        return reservationService.getReservationsByClient(clientId);
    }

    @GetMapping("/status/{statut}")
    public List<Reservation> getReservationsByStatut(@PathVariable String statut) {
        try {
            Reservation.Statut statusEnum = Reservation.Statut.valueOf(statut.toUpperCase());
            return reservationService.getReservationsByStatut(statusEnum);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    @PostMapping("/make")
    public ResponseEntity<Reservation> makeReservation(@RequestParam Long propertyId,
                                                       @RequestBody Client client,
                                                       @RequestParam String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            Reservation reservation = reservationService.makeReservation(propertyId, client, localDate);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{reservationId}/validate")
    public ResponseEntity<Reservation> validateReservation(@PathVariable Long reservationId) {
        try {
            Reservation reservation = reservationService.validateReservation(reservationId);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

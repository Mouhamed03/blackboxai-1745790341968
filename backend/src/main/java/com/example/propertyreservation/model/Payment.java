package com.example.propertyreservation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private LocalDate date;
    private double montant;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Payment(String reference, LocalDate date, double montant, Reservation reservation) {
        this.reference = reference;
        this.date = date;
        this.montant = montant;
        this.reservation = reservation;
    }
}

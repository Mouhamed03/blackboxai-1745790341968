package com.example.propertyreservation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Reservation {

    public enum Statut {
        ENCOURS, VALIDER, ANNULER, PAYER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.ENCOURS;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    public Reservation(LocalDate date, Property property, Client client) {
        this.date = date;
        this.property = property;
        this.client = client;
        this.statut = Statut.ENCOURS;
    }
}

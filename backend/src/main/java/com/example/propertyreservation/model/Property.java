package com.example.propertyreservation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Property {

    public enum Type {
        APPARTEMENT, MAISON, STUDIO
    }

    public enum Statut {
        DISPONIBLE, RESERVER, ARCHIVER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String adresse;
    private double prix;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.DISPONIBLE;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public Property(String reference, String adresse, double prix, Type type, Owner owner) {
        this.reference = reference;
        this.adresse = adresse;
        this.prix = prix;
        this.type = type;
        this.owner = owner;
        this.statut = Statut.DISPONIBLE;
    }
}

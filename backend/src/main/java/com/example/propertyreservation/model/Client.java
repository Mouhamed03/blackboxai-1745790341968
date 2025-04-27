package com.example.propertyreservation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String telephone;
    private String adresse;

    public Client(String nom, String telephone, String adresse) {
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
    }
}

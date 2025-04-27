package com.example.propertyreservation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @ElementCollection
    @CollectionTable(name = "owner_phones", joinColumns = @JoinColumn(name = "owner_id"))
    @Column(name = "phone")
    private List<String> telephones = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> biens = new ArrayList<>();

    public Owner(String nom, String prenom, List<String> telephones) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephones = telephones;
    }
}

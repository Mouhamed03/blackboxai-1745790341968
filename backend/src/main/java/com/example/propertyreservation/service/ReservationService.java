package com.example.propertyreservation.service;

import com.example.propertyreservation.model.*;
import com.example.propertyreservation.model.Reservation.Statut;
import com.example.propertyreservation.repository.PropertyRepository;
import com.example.propertyreservation.repository.ReservationRepository;
import com.example.propertyreservation.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PropertyRepository propertyRepository;
    private final ClientRepository clientRepository;

    public ReservationService(ReservationRepository reservationRepository, PropertyRepository propertyRepository, ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.propertyRepository = propertyRepository;
        this.clientRepository = clientRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByProperty(Long propertyId) {
        return reservationRepository.findByPropertyId(propertyId);
    }

    public List<Reservation> getReservationsByClient(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }

    public List<Reservation> getReservationsByStatut(Reservation.Statut statut) {
        return reservationRepository.findByStatut(statut);
    }

    @Transactional
    public Reservation makeReservation(Long propertyId, Client client, LocalDate date) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
        if (property.getStatut() != com.example.propertyreservation.model.Property.Statut.DISPONIBLE) {
            throw new IllegalStateException("Property is not available for reservation");
        }
        Client savedClient = clientRepository.save(client);
        Reservation reservation = new Reservation(date, property, savedClient);
        reservation.setStatut(Reservation.Statut.ENCOURS);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation validateReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        if (reservation.getStatut() != Reservation.Statut.ENCOURS) {
            throw new IllegalStateException("Only reservations in ENCOURS state can be validated");
        }
        // Set this reservation to VALIDER
        reservation.setStatut(Reservation.Statut.VALIDER);
        reservationRepository.save(reservation);

        // Cancel all other reservations on the same property
        List<Reservation> otherReservations = reservationRepository.findByPropertyId(reservation.getProperty().getId());
        for (Reservation other : otherReservations) {
            if (!other.getId().equals(reservationId) && other.getStatut() == Reservation.Statut.ENCOURS) {
                other.setStatut(Reservation.Statut.ANNULER);
                reservationRepository.save(other);
            }
        }

        // Set property status to RESERVER
        Property property = reservation.getProperty();
        property.setStatut(com.example.propertyreservation.model.Property.Statut.RESERVER);
        propertyRepository.save(property);

        return reservation;
    }
}

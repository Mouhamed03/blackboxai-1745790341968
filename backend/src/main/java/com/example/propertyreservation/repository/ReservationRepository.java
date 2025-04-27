package com.example.propertyreservation.repository;

import com.example.propertyreservation.model.Reservation;
import com.example.propertyreservation.model.Reservation.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByPropertyId(Long propertyId);
    List<Reservation> findByClientId(Long clientId);
    List<Reservation> findByStatut(Statut statut);
}

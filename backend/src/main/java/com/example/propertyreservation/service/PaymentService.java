package com.example.propertyreservation.service;

import com.example.propertyreservation.model.Payment;
import com.example.propertyreservation.model.Reservation;
import com.example.propertyreservation.repository.PaymentRepository;
import com.example.propertyreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentService(PaymentRepository paymentRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Payment recordPayment(String reference, LocalDate date, double montant, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatut() != Reservation.Statut.VALIDER) {
            throw new IllegalStateException("Only validated reservations can have payments");
        }

        Payment payment = new Payment(reference, date, montant, reservation);
        Payment savedPayment = paymentRepository.save(payment);

        // Update reservation status to PAYER
        reservation.setStatut(Reservation.Statut.PAYER);
        reservationRepository.save(reservation);

        return savedPayment;
    }
}

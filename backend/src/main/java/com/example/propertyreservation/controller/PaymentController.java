package com.example.propertyreservation.controller;

import com.example.propertyreservation.model.Payment;
import com.example.propertyreservation.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/record")
    public ResponseEntity<Payment> recordPayment(@RequestParam String reference,
                                                 @RequestParam String date,
                                                 @RequestParam double montant,
                                                 @RequestParam Long reservationId) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            Payment payment = paymentService.recordPayment(reference, localDate, montant, reservationId);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.example.propertyreservation.controller;

import com.example.propertyreservation.model.Owner;
import com.example.propertyreservation.model.Property;
import com.example.propertyreservation.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PostMapping
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        try {
            Owner savedOwner = ownerService.addOwner(owner);
            return ResponseEntity.ok(savedOwner);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{ownerId}/properties")
    public List<Property> getPropertiesByOwner(@PathVariable Long ownerId) {
        return ownerService.getPropertiesByOwner(ownerId);
    }

    @PostMapping("/{ownerId}/properties")
    public ResponseEntity<Property> addPropertyToOwner(@PathVariable Long ownerId, @RequestBody Property property) {
        try {
            Property savedProperty = ownerService.addPropertyToOwner(ownerId, property);
            return ResponseEntity.ok(savedProperty);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/properties/{propertyId}/archive")
    public ResponseEntity<Property> archiveProperty(@PathVariable Long propertyId) {
        try {
            Property property = ownerService.archiveProperty(propertyId);
            return ResponseEntity.ok(property);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/properties/{propertyId}/activate")
    public ResponseEntity<Property> activateProperty(@PathVariable Long propertyId) {
        try {
            Property property = ownerService.activateProperty(propertyId);
            return ResponseEntity.ok(property);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

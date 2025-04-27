package com.example.propertyreservation.service;

import com.example.propertyreservation.model.Owner;
import com.example.propertyreservation.model.Property;
import com.example.propertyreservation.repository.OwnerRepository;
import com.example.propertyreservation.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;

    public OwnerService(OwnerRepository ownerRepository, PropertyRepository propertyRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    @Transactional
    public Owner addOwner(Owner owner) {
        // Validate phone numbers
        for (String phone : owner.getTelephones()) {
            if (!phone.matches("^(77|78|76|70)\\d{7}$")) {
                throw new IllegalArgumentException("Invalid phone number: " + phone);
            }
        }
        // Add at least one property with status DISPONIBLE
        if (owner.getBiens() == null || owner.getBiens().isEmpty()) {
            throw new IllegalArgumentException("Owner must have at least one property");
        }
        for (Property property : owner.getBiens()) {
            property.setStatut(com.example.propertyreservation.model.Property.Statut.DISPONIBLE);
            property.setOwner(owner);
        }
        return ownerRepository.save(owner);
    }

    public List<Property> getPropertiesByOwner(Long ownerId) {
        return propertyRepository.findByOwnerId(ownerId);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public List<Property> filterPropertiesByType(Property.Type type) {
        return propertyRepository.findByType(type);
    }

    public List<Property> filterPropertiesByStatut(com.example.propertyreservation.model.Property.Statut statut) {
        return propertyRepository.findByStatut(statut);
    }

    @Transactional
    public Property addPropertyToOwner(Long ownerId, Property property) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        property.setOwner(owner);
        property.setStatut(com.example.propertyreservation.model.Property.Statut.DISPONIBLE);
        return propertyRepository.save(property);
    }

    @Transactional
    public Property archiveProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
        property.setStatut(com.example.propertyreservation.model.Property.Statut.ARCHIVER);
        return propertyRepository.save(property);
    }

    @Transactional
    public Property activateProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
        property.setStatut(com.example.propertyreservation.model.Property.Statut.DISPONIBLE);
        return propertyRepository.save(property);
    }
}

package com.example.propertyreservation.repository;

import com.example.propertyreservation.model.Property;
import com.example.propertyreservation.model.Property.Statut;
import com.example.propertyreservation.model.Property.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByType(Type type);
    List<Property> findByStatut(Statut statut);
    List<Property> findByOwnerId(Long ownerId);
}

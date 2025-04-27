import React from 'react';

function Manager() {
  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Manager Menu</h1>
      <p>This is the Manager interface. Implement forms and lists for:</p>
      <ul className="list-disc list-inside">
        <li>Ajouter un Proprietaire dans une Liste</li>
        <li>Lister les Propriétaires</li>
        <li>Ajouter un nouveau bien à un Proprietaire</li>
        <li>Afficher la liste des Biens d’un Proprietaire</li>
        <li>Lister les Biens avec filtres (Type, Statut, Proprietaire)</li>
        <li>Lister les Réservations avec filtres (Bien, Client, Statut)</li>
        <li>Valider une Réservation</li>
        <li>Enregistrer le Paiement d’une Réservation</li>
        <li>Archiver/Activer un Bien</li>
      </ul>
      <p>Functionality to be implemented.</p>
    </div>
  );
}

export default Manager;

import React from 'react';

function Client() {
  return (
    <div>
      <h1 className="text-2xl font-bold mb-4">Client Menu</h1>
      <p>This is the Client interface. Implement forms and lists for:</p>
      <ul className="list-disc list-inside">
        <li>Lister les Biens Disponible</li>
        <li>Filtrer par Type (Appartement/Maison/Studio)</li>
        <li>Faire une Reservation sur un bien</li>
        <li>Lister ses Réservations avec filtres (Bien, Statut)</li>
      </ul>
      <p>Functionality to be implemented.</p>
    </div>
  );
}

export default Client;

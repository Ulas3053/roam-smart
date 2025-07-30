// src/components/ItineraryResult.jsx
import React from 'react';
import { useLocation } from 'react-router-dom';
import '../css/itinerary.css';

const ItineraryResult = () => {
  const { state } = useLocation();

  if (!state) return <p>No itinerary data provided.</p>;

  const { place, budget, days, people } = state;

  return (
    <div className="itinerary-result-container">
      <h2>Itinerary for {place.name}</h2>
      <p><strong>Location:</strong> {place.formattedAddress}</p>
      <p><strong>Budget:</strong> ₹{budget}</p>
      <p><strong>Days:</strong> {days}</p>
      <p><strong>People:</strong> {people}</p>

      <div className="plan-box">
        <p>✨ Coming soon: AI-generated itinerary based on your inputs!</p>
      </div>
    </div>
  );
};

export default ItineraryResult;

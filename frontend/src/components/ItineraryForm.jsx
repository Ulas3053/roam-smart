// src/components/ItineraryForm.jsx
import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import '../css/itinerary.css';

const ItineraryForm = () => {
  const { state } = useLocation();
  const place = state?.place;
  const navigate = useNavigate();

  const [budget, setBudget] = useState('');
  const [days, setDays] = useState('');
  const [people, setPeople] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!budget || !days || !people) return;

    navigate('/itinerary-result', {
      state: {
        place,
        budget,
        days,
        people
      }
    });
  };

  return (
    <div className="itinerary-form-container">
      <h2>Generate Itinerary for {place || 'Selected Place'}</h2>
      <form onSubmit={handleSubmit}>
        <label>Budget (₹):</label>
        <input type="number" value={budget} onChange={(e) => setBudget(e.target.value)} required />

        <label>Days:</label>
        <input type="number" value={days} onChange={(e) => setDays(e.target.value)} required />

        <label>No. of People:</label>
        <input type="number" value={people} onChange={(e) => setPeople(e.target.value)} required />

        <button type="submit">Get Plan</button>
      </form>
    </div>
  );
};

export default ItineraryForm;

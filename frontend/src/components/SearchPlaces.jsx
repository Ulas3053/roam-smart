// src/components/SearchPlaces.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../css/searchplaces.css';

const SearchPlaces = () => {
  const [place, setPlace] = useState('');
  const [results, setResults] = useState([]);
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSearch = async () => {
    if (!place.trim()) return;

    setLoading(true);
    setMessage('');
    setResults([]);

    try {
      const res = await axios.get(`/api/place-search/explore?place=${place}`);
      const data = res.data['Places around :'];
      if (!data || data.length === 0) {
        setMessage('No places found around that location.');
      } else {
        setResults(data);
      }
    } catch (err) {
      setMessage('Error fetching places. Please try again.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleGenerateItinerary = () => {
    navigate('/generate-itinerary', { state: { place } });
  };

  return (
    <div className="search-places-container">
      <h2>Search Places Around</h2>
      <div className="input-group">
        <input
          type="text"
          value={place}
          onChange={(e) => setPlace(e.target.value)}
          placeholder="Enter a place (e.g., Bengaluru)"
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {loading && <p>Loading...</p>}
      {message && <p className="info-msg">{message}</p>}

      <div className="places-grid">
        {results.map((place, index) => (
          <div key={index} className="place-card">
            <h4>{place.name || 'Unnamed Location'}</h4>
            <p>{place.formattedAddress}</p>
            <p>📍 {place.lat}, {place.lon}</p>
          </div>
        ))}
      </div>

      {/* Show this button only if results are present */}
      {results.length > 0 && (
        <div style={{ marginTop: '2rem', textAlign: 'center' }}>
          <button className="generate-btn" onClick={handleGenerateItinerary}>
            Generate Itinerary for "{place}"
          </button>
        </div>
      )}
    </div>
  );
};

export default SearchPlaces;

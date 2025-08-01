// src/components/SearchPlaces.jsx
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../css/searchplaces.css";

const formatTime = (unixTimestamp, timezoneOffsetSeconds) => {
  const localMillis = (unixTimestamp + timezoneOffsetSeconds) * 1000;
  const d = new Date(localMillis);
  let hours = d.getUTCHours();
  const minutes = d.getUTCMinutes();
  const ampm = hours >= 12 ? "PM" : "AM";
  hours = hours % 12 || 12;
  const pad = (n) => String(n).padStart(2, "0");
  return `${pad(hours)}:${pad(minutes)} ${ampm}`;
};

const SearchPlaces = () => {
  const [place, setPlace] = useState("");
  const [results, setResults] = useState([]);
  const [rawWeather, setRawWeather] = useState(null);
  const [weatherObj, setWeatherObj] = useState(null);
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSearch = async () => {
    if (!place.trim()) return;

    setLoading(true);
    setMessage("");
    setResults([]);
    setRawWeather(null);
    setWeatherObj(null);

    const token = localStorage.getItem("token");

    try {
      const [placesRes, weatherRes] = await Promise.all([
        axios.get(
          `/api/place-search/explore?place=${encodeURIComponent(place)}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        ),
        axios.get(`/api/weather/${encodeURIComponent(place)}`, {
          headers: { Authorization: `Bearer ${token}` },
        }),
      ]);

      const placesData = placesRes.data["Places around :"];
      if (!placesData || placesData.length === 0) {
        setMessage("No places found around that location.");
      } else {
        setResults(placesData);
      }

      setRawWeather(weatherRes.data);
      try {
        const parsed = JSON.parse(weatherRes.data.weather);
        setWeatherObj(parsed);
      } catch (e) {
        console.error("Failed to parse inner weather JSON:", e);
      }
    } catch (err) {
      console.error(err);
      setMessage("Error fetching data. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleGenerateItinerary = () => {
    navigate("/generate-itinerary", { state: { place } });
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

      {loading && <p className="info-msg">Loading...</p>}
      {message && <p className="info-msg">{message}</p>}

      {weatherObj && (
        <div className="weather-box">
          <div className="weather-header">
            <h3>Weather in {rawWeather?.city || place}</h3>
            {weatherObj.weather && weatherObj.weather[0] && (
              <div className="weather-main">
                <img
                  src={`https://openweathermap.org/img/wn/${weatherObj.weather[0].icon}@2x.png`}
                  alt={weatherObj.weather[0].description}
                  className="weather-icon"
                />
                <div className="weather-desc">
                  <div className="temp">
                    {Math.round(weatherObj.main.temp)}°C
                    <span className="feels">
                      Feels like {Math.round(weatherObj.main.feels_like)}°C
                    </span>
                  </div>
                  <div className="desc-text">
                    {weatherObj.weather[0].description}
                  </div>
                </div>
              </div>
            )}
          </div>
          <div className="weather-details">
            <div>
              <strong>Humidity:</strong> {weatherObj.main.humidity}%
            </div>
            <div>
              <strong>Pressure:</strong> {weatherObj.main.pressure} hPa
            </div>
            <div>
              <strong>Wind:</strong> {weatherObj.wind.speed} m/s @
              {weatherObj.wind.deg}°
            </div>
            <div>
              <strong>Sunrise:</strong>{" "}
              {formatTime(weatherObj.sys.sunrise, weatherObj.timezone)}
            </div>
            <div>
              <strong>Sunset:</strong>{" "}
              {formatTime(weatherObj.sys.sunset, weatherObj.timezone)}
            </div>
          </div>
        </div>
      )}

      <h1>Places to Explore</h1>

      <div className="places-grid">
        {results.map((p, index) => (
          <div key={index} className="place-card">
            <h4>{p.name || "Unnamed Location"}</h4>
            <p>{p.formattedAddress}</p>
            <p>
              📍 {p.lat}, {p.lon}
            </p>
          </div>
        ))}
      </div>

      {results.length > 0 && (
        <div
          className="itinerary-cta"
          style={{ textAlign: "center", marginTop: "1.5rem" }}
        >
          <button className="generate-btn" onClick={handleGenerateItinerary}>
            Generate Itinerary for "{place}"
          </button>
        </div>
      )}
    </div>
  );
};

export default SearchPlaces;

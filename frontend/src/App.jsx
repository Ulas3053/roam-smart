import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css'; // Ensure you have your global styles imported
import Register from './pages/Register';
import Otp from './pages/Otp';
import Login from './pages/Login';
import HomePage from './pages/Homepage';
import ItineraryForm from './components/ItineraryForm';
import ItineraryResult from './components/ItineraryResult';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Register />} />
        <Route path="/verify-otp" element={<Otp />} />
        <Route path="/login" element={<Login />} />
        <Route path="/homepage" element={<HomePage />} />
        <Route path="/generate-itinerary" element={<ItineraryForm />} />
        <Route path="/itinerary-result" element={<ItineraryResult />} />
      </Routes>
    </Router>
  );
}

export default App;

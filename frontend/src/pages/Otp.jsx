// src/pages/Otp.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../css/otp.css';
import logo from '../images/RoamSmart Logo.png';

const Otp = () => {
  const [otp, setOtp] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`/api/auth/otp?otp=${otp}`);
      console.log(response.data);
      setMessage('OTP verified successfully! Redirecting to login...');
      setTimeout(() => navigate('/login'), 1500);
    } catch (err) {
      console.error(err);
      setMessage('Invalid OTP. Please try again.');
    }
  };

  return (
    <div className="page-wrapper">
    <div className="otp-layout">
      <div className="otp-left">
        <div className="form-wrapper">
          <h2>Verify OTP</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              name="otp"
              placeholder="Enter OTP sent to your email"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
            />
            <button type="submit">Verify</button>
          </form>
          <p className="msg">{message}</p>
        </div>
      </div>
      <div className="otp-right">
        <div className="branding">
          <img src={logo} alt="RoamSmart Logo" height={180} width={180} style={{ borderRadius: '50%' }} />
          <h1>RoamSmart</h1>
          <p className="tagline">Explore smarter. Plan better.<br />Your intelligent travel partner.</p>
        </div>
      </div>
    </div>
    </div>
  );
};

export default Otp;

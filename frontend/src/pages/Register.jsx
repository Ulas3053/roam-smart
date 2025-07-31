// src/pages/Register.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import '../css/reg.css';
import logo from '../images/RoamSmart Logo.png';


const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    password: '',
    confirmPassword: ''
  });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      setMessage("Passwords do not match.");
      return;
    }

    try {
      const response = await axios.post('/api/auth/register', formData);
      console.log(response.data);
      setMessage('Registration successful!');
      setTimeout(() => navigate('/verify-otp'), 1500);
    } catch (err) {
      console.error(err);
      setMessage('Registration failed. Please check your input.');
    }
  };

  return (
    <div className="page-wrapper">
    <div className="reg-layout">
      <div className="reg-left">
        <div className="form-wrapper">
          <h2>Create Your Account</h2>
          <form onSubmit={handleSubmit}>
            <input name="name" placeholder="Full Name" onChange={handleChange} value={formData.name} />
            <input name="email" type="email" placeholder="Email Address" onChange={handleChange} value={formData.email} />
            <input name="phone" type="text" placeholder="Phone Number" onChange={handleChange} value={formData.phone} />
            <input name="password" type="password" placeholder="Password" onChange={handleChange} value={formData.password} />
            <input name="confirmPassword" type="password" placeholder="Confirm Password" onChange={handleChange} value={formData.confirmPassword} />
            <button type="submit">Register</button>
          </form>
          <p className="msg">{message}</p>
          <p className="login-redirect">
            Already have an account? <Link to="/login">Login</Link>
          </p>
        </div>
      </div>
      <div className="reg-right">
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

export default Register;
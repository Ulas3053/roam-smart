// src/pages/Login.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../css/login.css';
import logo from '../images/RoamSmart Logo.png';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/api/auth/login', { email, password });
      console.log(response.data);
      localStorage.setItem('token', response.data.token); // optional
      setMessage('Login successful!');
      setTimeout(() => navigate('/dashboard'), 1500); // adjust path as needed
    } catch (err) {
      console.error(err);
      setMessage('Invalid email or password.');
    }
  };

  return (
    <div className="login-layout">
      <div className="login-left">
        <div className="form-wrapper">
          <h2>Login to Your Account</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="email"
              placeholder="Email Address"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <button type="submit">Login</button>
          </form>
          <p className="msg">{message}</p>
          <p className="reg-redirect">
            Don’t have an account? <a href="/">Register</a>
          </p>
        </div>
      </div>
      <div className="login-right">
        <div className="branding">
          <img src={logo} alt="RoamSmart Logo" height={180} width={180} style={{ borderRadius: '50%' }} />
          <h1>RoamSmart</h1>
          <p className="tagline">Explore smarter. Plan better.<br />Your intelligent travel partner.</p>
        </div>
      </div>
    </div>
  );
};

export default Login;

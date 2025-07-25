import React, { useState } from 'react';
import axios from 'axios';
import '../css/reg.css';

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    password: '',
    confirmPassword: ''
  });

  const [message, setMessage] = useState('');

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
      const response = await axios.post('/api/auth/register', formData); // proxy handles base URL
      console.log(response.data);
      setMessage('Registration successful!');
    } catch (err) {
      console.error(err);
      setMessage('Registration failed. Please check your input.');
    }
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <input name="name" placeholder="Name" onChange={handleChange} value={formData.name} />
        <input name="email" type="email" placeholder="Email" onChange={handleChange} value={formData.email} />
        <input name="phone" type="text" placeholder="Phone" onChange={handleChange} value={formData.phone} />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} value={formData.password} />
        <input name="confirmPassword" type="password" placeholder="Confirm Password" onChange={handleChange} value={formData.confirmPassword} />
        <button type="submit">Register</button>
      </form>
      <p>{message}</p>
    </div>
  );
};

export default Register;

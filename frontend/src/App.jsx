import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './pages/Register';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<h1>Welcome to RoamSmart! <Register></Register></h1>} />
        <Route path="/register" element={<Register></Register>} />
      </Routes>
    </Router>
  );
}

export default App;

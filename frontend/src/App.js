import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Manager from './pages/Manager';
import Client from './pages/Client';

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <nav className="bg-white shadow p-4 flex justify-between">
          <div className="text-xl font-bold">Property Reservation</div>
          <div className="space-x-4">
            <Link to="/manager" className="text-blue-600 hover:underline">Manager</Link>
            <Link to="/client" className="text-blue-600 hover:underline">Client</Link>
          </div>
        </nav>
        <main className="p-4">
          <Routes>
            <Route path="/manager" element={<Manager />} />
            <Route path="/client" element={<Client />} />
            <Route path="*" element={<div>Welcome! Please select Manager or Client.</div>} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;

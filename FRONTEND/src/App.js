// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import ForgotPassword from './pages/ForgotPassword';
import ResetPassword from './styles/ResetPassword';
import Dashboard from './pages/Dashboard';
import UserManagement from './pages/UserManagement';
import ManualOrderEntry from './pages/ManualOrderEntry';
import InventoryStaff from './pages/InventoryStaff';
import PaymentProcessing from './pages/PaymentProcessing';
import Reporting from './pages/Reporting';
import ManagerDashboard from './pages/ManagerDashboard';
import Profile from './pages/Profile';
import POS from './pages/POS';
import CustomerManagement from './pages/CustomerManagement';
import Reportingpage from './pages/Reportingpage';

import Layout from './components/Layout';
import ProtectedRoute from './components/ProtectedRoute';



function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Dashboard />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/ResetPassword" element={<ResetPassword />} />
        <Route path="/UserManagement" element={<UserManagement />} />
        <Route path="/Profile" element={<Profile />} />   
        <Route path="/POS" element={<POS />} />
        <Route path="/InventoryStaff" element={<InventoryStaff />} />     
        <Route path="/CustomerManagement" element={<CustomerManagement />} />
        <Route  path="/Reportingpage" element={<Reportingpage />} />
          <Route
            path="/dashboard"
            element={
              <ProtectedRoute>
                <Dashboard />
              </ProtectedRoute>
            }
          />
          <Route
            path="/orders"
            element={
              <ProtectedRoute>
                <ManualOrderEntry />
              </ProtectedRoute>
            }
          />
          <Route
            path="/inventory"
            element={
              <ProtectedRoute>
                <InventoryStaff />
              </ProtectedRoute>
            }
          />
          <Route
            path="/payment"
            element={
              <ProtectedRoute>
                <PaymentProcessing />
              </ProtectedRoute>
            }
          />
          <Route
            path="/reporting"
            element={
              <ProtectedRoute>
                <Reporting />
              </ProtectedRoute>
            }
          />
          <Route
            path="/manager-dashboard"
            element={
              <ProtectedRoute>
                <ManagerDashboard />
              </ProtectedRoute>
            }
          />
        <Route path="/" element={<Login />} />
      </Routes>
    </Router>
  );
}

export default App;
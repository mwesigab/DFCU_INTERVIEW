/* eslint-disable react/jsx-props-no-spreading */
import { Navigate, Outlet } from 'react-router-dom';
import React from 'react';
import { getCookie } from '../../services/cookies';

export const PrivateRoute = () => {
  const auth =
    getCookie('loggedInUser') != null && getCookie('loggedInUser').loggedIn;

  // If authorized, return an outlet that will render child elements
  // If not, return element that will navigate to login page
  return auth ? <Outlet /> : <Navigate to="/" />;
};

import './App.css';
import { Routes, Route } from 'react-router-dom';
import { Dashboard } from './components/dashboard';
import { SignInComponent } from './components/auth/sign-in';
import { SignUpComponent } from './components/auth/sign-up';
import { PrivateRoute } from './components/auth/privateRoute';

function App() {
  return (
    <>
      <Routes>
        <Route exact path="/" element={<SignInComponent />} />
        <Route exact path="/sign-up" element={<SignUpComponent />} />

        <Route exact path="/" element={<PrivateRoute />}>
          <Route exact path="/dashboard" element={<Dashboard />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;

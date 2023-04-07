import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { post } from '../../services/RemoteHelper';
import toast, { Toaster } from 'react-hot-toast';
import { getCookie, logOut as clearCookie } from '../../services/cookies';

export const AppTopbar = () => {
  const navigate = useNavigate();
  const handleLogout = () => {
    const toastId = toast.loading('Signing you out now...');
    const { user } = getCookie('loggedInUser');
    post('/logout', user, ({ returnCode, returnMessage, returnData }) => {
      toast.dismiss(toastId);

      if (returnCode === 200 && returnData > 0) {
        toast.success(returnMessage, {
          id: toastId
        });

        clearCookie();

        navigate('/');
      } else
        toast.error(returnMessage, {
          id: toastId
        });
    });
  };
  return (
    <header className="main-header navbar">
      <Toaster />
      <div className="col-search">
        <form className="searchform">
          <div className="input-group">
            <input
              list="search_terms"
              type="text"
              className="form-control"
              placeholder="Search..."
            />
            <button className="btn btn-light bg" type="button">
              <i className="material-icons md-search"></i>
            </button>
          </div>
          <datalist id="search_terms">
            <option value="Validations" />
            <option value="Requests" />
            <option value="Postive Requests" />
            <option value="Negative Requests" />
          </datalist>
        </form>
      </div>
      <div className="col-nav">
        <button
          className="btn btn-icon btn-mobile me-auto"
          data-trigger="#offcanvas_aside"
        >
          <i className="md-28 material-icons md-menu"></i>
        </button>
        <ul className="nav">
          <li className="nav-item">
            <Link
              className="nav-link btn-icon"
              onClick="darkmode(this)"
              title="Dark mode"
              to="/"
            >
              <i className="material-icons md-nights_stay"></i>
            </Link>
          </li>
          <li className="nav-item">
            <Link to="/" className="nav-link btn-icon">
              <i className="material-icons md-notifications_active"></i>
            </Link>
          </li>
          <li className="dropdown nav-item">
            <Link to="/" className="dropdown-toggle" data-bs-toggle="dropdown">
              <img
                className="img-xs rounded-circle"
                src="images/people/user.png"
                alt="User"
              />
            </Link>
            <div className="dropdown-menu dropdown-menu-end">
              <div onClick={handleLogout} className="dropdown-item text-danger">
                Logout
              </div>
            </div>
          </li>
        </ul>
      </div>
    </header>
  );
};

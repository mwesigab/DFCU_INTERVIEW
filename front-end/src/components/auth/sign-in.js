import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import toast, { Toaster } from 'react-hot-toast';
import { Formik } from 'formik';
import { post } from '../../services/RemoteHelper';
import { colors } from '../../resources/global';
import { setCookie } from '../../services/cookies';

export const SignInComponent = () => {
  const [remember, setRemember] = useState(true);

  const initialValues = {
    username: '',
    password: ''
  };

  const navigate = useNavigate();

  return (
    <main>
      <section className="content-main">
        <div
          className="card shadow mx-auto"
          style={{ maxWidth: '380px', marginTop: '100px' }}
        >
          <div className="card-body">
            <h4 className="card-title mb-4">Sign in</h4>
            <Formik
              initialValues={initialValues}
              validate={(values) => {
                const errors = {};

                if (!values.password) errors.password = 'Password Required';

                if (!values.username) {
                  errors.username = 'This Field Required';
                } else if (
                  !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(
                    values.username
                  )
                ) {
                  errors.username = 'Invalid Username';
                }
                return errors;
              }}
              onSubmit={(values, { setSubmitting }) => {
                const toastId = toast.loading('Signing you in now...');
                post(
                  '/login',
                  values,
                  ({ returnCode, returnMessage, returnData }) => {
                    toast.dismiss(toastId);
                    if (returnCode === 200) {
                      toast.success(returnMessage, {
                        id: toastId
                      });

                      const authObject = {
                        user: returnData,
                        loggedIn: returnData.loggedIn,
                        token: returnData?.token
                      };

                      setCookie('loggedInUser', authObject);

                      navigate('/dashboard');
                    } else
                      toast.error(returnMessage, {
                        id: toastId
                      });
                    setSubmitting(false);
                  }
                );
              }}
            >
              {({
                values,
                errors,
                touched,
                handleChange,
                handleBlur,
                handleSubmit,
                isSubmitting
              }) => (
                <form onSubmit={handleSubmit}>
                  <Toaster />
                  <div className="mb-3">
                    <input
                      className="form-control"
                      placeholder="Username or email"
                      type="text"
                      onChange={handleChange('username')}
                      onBlur={handleBlur('username')}
                      value={values.username}
                    />
                    <div style={{ color: colors.red }}>
                      {errors.username && touched.username && errors.username}
                    </div>
                  </div>
                  <div className="mb-3">
                    <input
                      className="form-control"
                      placeholder="Password"
                      type="password"
                      onChange={handleChange('password')}
                      onBlur={handleBlur('password')}
                      value={values.password}
                    />
                    <div style={{ color: colors.red }}>
                      {errors.password && touched.password && errors.password}
                    </div>
                  </div>

                  <div className="mb-3">
                    <Link to="/" className="float-end">
                      Forgot password?
                    </Link>
                    <label className="form-check">
                      <input
                        type="checkbox"
                        className="form-check-input"
                        checked={remember}
                        onChange={() => setRemember(!remember)}
                      />
                      <span className="form-check-label">Remember</span>
                    </label>
                  </div>
                  <div className="mb-4">
                    <button
                      disabled={isSubmitting}
                      type="submit"
                      className="btn btn-primary w-100"
                    >
                      Login
                    </button>
                  </div>
                </form>
              )}
            </Formik>
            <p className="text-center mb-4">
              Don't have account? <Link to="/sign-up">Sign up</Link>
            </p>
            <div className="d-grid gap-3 mb-4">
              <Link to="/" className="btn w-100 btn-light">
                <svg
                  aria-hidden="true"
                  className="icon-svg"
                  width="20"
                  height="20"
                  viewBox="0 0 20 20"
                >
                  <path
                    d="M16.51 8H8.98v3h4.3c-.18 1-.74 1.48-1.6 2.04v2.01h2.6a7.8 7.8 0 002.38-5.88c0-.57-.05-.66-.15-1.18z"
                    fill="#4285F4"
                  ></path>
                  <path
                    d="M8.98 17c2.16 0 3.97-.72 5.3-1.94l-2.6-2a4.8 4.8 0 01-7.18-2.54H1.83v2.07A8 8 0 008.98 17z"
                    fill="#34A853"
                  ></path>
                  <path
                    d="M4.5 10.52a4.8 4.8 0 010-3.04V5.41H1.83a8 8 0 000 7.18l2.67-2.07z"
                    fill="#FBBC05"
                  ></path>
                  <path
                    d="M8.98 4.18c1.17 0 2.23.4 3.06 1.2l2.3-2.3A8 8 0 001.83 5.4L4.5 7.49a4.77 4.77 0 014.48-3.3z"
                    fill="#EA4335"
                  ></path>
                </svg>
                Sign up using Google
              </Link>

              <Link to="/" className="btn w-100 btn-light">
                <svg
                  aria-hidden="true"
                  className="icon-svg"
                  width="20"
                  height="20"
                  viewBox="0 0 20 20"
                >
                  <path
                    d="M3 1a2 2 0 00-2 2v12c0 1.1.9 2 2 2h12a2 2 0 002-2V3a2 2 0 00-2-2H3zm6.55 16v-6.2H7.46V8.4h2.09V6.61c0-2.07 1.26-3.2 3.1-3.2.88 0 1.64.07 1.87.1v2.16h-1.29c-1 0-1.19.48-1.19 1.18V8.4h2.39l-.31 2.42h-2.08V17h-2.5z"
                    fill="#4167B2"
                  ></path>
                </svg>
                Sign up using Facebook
              </Link>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
};

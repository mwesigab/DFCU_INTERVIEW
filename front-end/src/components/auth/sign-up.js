import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { COUNTRIES_DATA } from '../../services/externalAPi';
import { Formik } from 'formik';
import { colors } from '../../resources/global';
import { post } from '../../services/RemoteHelper';
import toast, { Toaster } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';

export const SignUpComponent = () => {
  const initialValues = {
    firstName: '',
    lastName: '',
    email: '',
    phoneCode: '+256',
    phone: '',
    password: ''
  };

  const navigate = useNavigate();

  useEffect(() => {}, []);

  return (
    <main>
      <section className="content-main">
        <div
          className="card shadow mx-auto"
          style={{ maxWidth: '400px', marginTop: '20px' }}
        >
          <div className="card-body">
            <h4 className="card-title mb-4">Sign Up</h4>
            <Formik
              initialValues={initialValues}
              validate={(values) => {
                const errors = {};

                if (!values.firstName) errors.firstName = 'First Name Required';

                if (!values.lastName) errors.lastName = 'Last Name Required';

                if (!values.phoneCode)
                  errors.phoneCode = 'Dialing Code Required';

                if (!values.phone) errors.phone = 'Phone Number Required';

                if (!values.password) errors.password = 'Password Required';

                if (!values.email) {
                  errors.email = 'Email Required';
                } else if (
                  !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
                ) {
                  errors.email = 'Invalid email address';
                }
                return errors;
              }}
              onSubmit={(values, { setSubmitting }) => {
                const toastId = toast.loading('Signing you up now...');
                post(
                  '/vendor/register',
                  {
                    ...values,
                    phone: values.phoneCode + values.phone
                  },
                  (response) => {
                    toast.dismiss(toastId);
                    if (response.success) {
                      toast.success(response.message, {
                        id: toastId
                      });

                      navigate('/sign-in');
                    } else
                      toast.error(response.message, {
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
                    <label className="form-label">First Name</label>
                    <input
                      className="form-control"
                      placeholder="E.g Benjamin"
                      type="text"
                      onChange={handleChange('firstName')}
                      onBlur={handleBlur('firstName')}
                      value={values.firstName}
                    />
                    <div style={{ color: colors.red }}>
                      {errors.firstName &&
                        touched.firstName &&
                        errors.firstName}
                    </div>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Last Name</label>
                    <input
                      className="form-control"
                      placeholder="E.g Mwesiga"
                      type="text"
                      onChange={handleChange('lastName')}
                      onBlur={handleBlur('lastName')}
                      value={values.lastName}
                    />
                    <div style={{ color: colors.red }}>
                      {errors.lastName && touched.lastName && errors.lastName}
                    </div>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input
                      className="form-control"
                      placeholder="mwesigab@gmail.com"
                      type="text"
                      onChange={handleChange('email')}
                      onBlur={handleBlur('email')}
                      value={values.email}
                    />
                    <div style={{ color: colors.red }}>
                      {errors.email && touched.email && errors.email}
                    </div>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Phone</label>
                    <div className="row gx-2">
                      <div className="col-5">
                        <select
                          className="form-select"
                          aria-label="Default select"
                          onChange={handleChange('phoneCode')}
                          onBlur={handleBlur('phoneCode')}
                          value={values.phoneCode}
                        >
                          {COUNTRIES_DATA.map((country) => (
                            <option
                              key={country.code}
                              value={country.dial_code}
                            >
                              {`${country.name} ${country.dial_code}`}
                            </option>
                          ))}
                        </select>
                        <div style={{ color: colors.red }}>
                          {errors.phoneCode &&
                            touched.phoneCode &&
                            errors.phoneCode}
                        </div>
                      </div>
                      <div className="col-7">
                        <input
                          className="form-control"
                          placeholder="Phone"
                          type="number"
                          onChange={handleChange('phone')}
                          onBlur={handleBlur('phone')}
                          value={values.phone}
                        />
                        <div style={{ color: colors.red }}>
                          {errors.phone && touched.phone && errors.phone}
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Create password</label>
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
                    <p className="small text-center text-muted">
                      By signing up, you confirm that you've read and accepted
                      our User Notice and Privacy Policy.
                    </p>
                  </div>
                  <div className="mb-4">
                    <button
                      type="submit"
                      disabled={isSubmitting}
                      className="btn btn-primary w-100"
                    >
                      Submit
                    </button>
                  </div>
                </form>
              )}
            </Formik>
            <p className="text-center small text-muted">or sign up with</p>
            <div className="d-flex gap-2 mb-4">
              <Link to="/" className="w-50 btn btn-light">
                <svg
                  aria-hidden="true"
                  className="icon-svg"
                  style={{ verticalAlign: 'bottom', marginTop: '-4px' }}
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
                Google
              </Link>

              <Link to="/" className="w-50 btn btn-light">
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
                Facebook
              </Link>
            </div>
            <p className="text-center mb-2">
              Have account? <Link to="/sign-in">Sign in</Link>
            </p>
          </div>
        </div>
      </section>
    </main>
  );
};

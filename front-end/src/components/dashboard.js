import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Main from './app-layout/main';
import { get } from '../services/RemoteHelper';

export const Dashboard = () => {
  const [logs, setLogs] = useState([]);
  const [validations, setValidations] = useState([]);
  const [positives, setPositives] = useState([]);
  const [negatives, setNegatives] = useState([]);

  useEffect(() => {
    getRequestLogs();
  }, []);

  const getRequestLogs = () => {
    const urls = [
      '/logs',
      '/logger/FAILED',
      '/logger/POSITIVE',
      '/logger/NEGATIVE'
    ];
    const promises = urls.map((url) => get(url, (response) => response));
    Promise.all(promises).then(([logsData, valData, negData, posData]) => {
      setLogs(logsData);
      setValidations(valData);
      setNegatives(negData);
      setPositives(posData);
    });
  };

  return (
    <>
      <Main />
      <main className="main-wrap">
        <section className="content-main">
          <div className="content-header">
            <h2 className="content-title"> Dashboard </h2>
            <div>
              <Link to="/dashboard" className="btn btn-primary">
                Create report
              </Link>
            </div>
          </div>
          <div className="row">
            <div className="col-lg-3">
              <div className="card card-body mb-4">
                <article className="icontext">
                  <span className="icon icon-sm rounded-circle bg-primary-light">
                    <i className="text-primary material-icons md-home"></i>
                  </span>
                  <div className="text">
                    <h6 className="mb-1">Total Requests</h6>
                    <span>{logs.length}</span>
                  </div>
                </article>
              </div>
            </div>
            <div className="col-lg-3">
              <div className="card card-body mb-4">
                <article className="icontext">
                  <span className="icon icon-sm rounded-circle bg-primary-light">
                    <i className="text-danger material-icons md-error"></i>
                  </span>
                  <div className="text">
                    <h6 className="mb-1">Failed Validations</h6>
                    <span>{validations}</span>
                  </div>
                </article>
              </div>
            </div>
            <div className="col-lg-3">
              <div className="card card-body mb-4">
                <article className="icontext">
                  <span className="icon icon-sm rounded-circle bg-success-light">
                    <i className="text-primary material-icons md-done"></i>
                  </span>
                  <div className="text">
                    <h6 className="mb-1">Positve Requests</h6>
                    <span>{positives}</span>
                  </div>
                </article>
              </div>
            </div>
            <div className="col-lg-3">
              <div className="card card-body mb-4">
                <article className="icontext">
                  <span className="icon icon-sm rounded-circle bg-warning-light">
                    <i className="text-warning material-icons md-close"></i>
                  </span>
                  <div className="text">
                    <h6 className="mb-1">Negative Requests</h6>
                    <span>{negatives}</span>
                  </div>
                </article>
              </div>
            </div>
          </div>
          <div className="card mb-4">
            <div className="card-body">
              <h5 className="card-title">Requests Log</h5>
              <div className="table-responsive">
                <table className="table table-hover">
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>Endpoint</th>
                      <th>Details</th>
                      <th>Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    {logs.map((log, index) => (
                      <tr key={index}>
                        <td>{index + 1}</td>
                        <td>
                          <b>{log?.endpoint}</b>
                        </td>
                        <td>{log?.details}</td>
                        <td>
                          {log?.status === 'POSITIVE' ? (
                            <span className="badge rounded-pill alert-success">
                              {log?.status}
                            </span>
                          ) : log?.status === 'NEGATIVE' ? (
                            <span className="badge rounded-pill alert-warning">
                              {log?.status}
                            </span>
                          ) : (
                            <span className="badge rounded-pill alert-danger">
                              {log?.status}
                            </span>
                          )}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </section>
      </main>
    </>
  );
};

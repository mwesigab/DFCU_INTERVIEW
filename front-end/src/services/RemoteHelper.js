import { getCookie } from './cookies';

const getToken = () => {
  const loggedInUser = getCookie('loggedInUser');
  return loggedInUser ? loggedInUser?.token : '';
};
const getApiUrl = () => {
  return process.env.REACT_APP_API_BASEURL;
};

export const post = async (url, dataToSend, responseHandler) => {
  const token = getToken();
  fetch(getApiUrl() + url, {
    method: 'POST',
    body: JSON.stringify(dataToSend),
    headers: {
      // Header Defination
      'Content-Type': 'application/json',
      Accept: 'application/json',
      Authorization: `Bearer ${token}`
    }
  })
    .then((response) => response.json())
    .then((responseJson) => {
      responseHandler(responseJson);
    })
    .catch((error) => {
      // Hide Loader
      console.log(error);
    });
};

export const put = async (url, dataToSend, responseHandler, errorHandler) => {
  const token = getToken();
  fetch(getApiUrl() + url, {
    method: 'PUT',
    body: JSON.stringify(dataToSend),
    headers: {
      // Header Defination
      'Content-Type': 'application/json',
      Accept: 'application/json',
      Authorization: `Bearer ${token}`
    }
  })
    .then((response) => response.json())
    .then((responseJson) => {
      responseHandler(responseJson);
    })
    .catch((error) => {
      errorHandler(error);
    });
};

export const get = async (url) => {
  const token = getToken();
  if (token) {
    return await fetch(getApiUrl() + url, {
      method: 'GET',
      body: null,
      headers: {
        // Header Defination
        'Content-Type': 'application/json',
        Accept: 'application/json',
        Authorization: `Bearer ${token}`
      }
    })
      .then((response) => response.json())
      .then(({ returnCode, returnData }) => {
        if (returnCode === 200) return returnData;
      })
      .catch((error) => {
        console.log(error);
      });
  }
};

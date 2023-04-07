import Cookies from 'universal-cookie';

const cookie = new Cookies();

export function setCookie(cookiename, value) {
  cookie.set(cookiename, value);
}
export function getCookie(cookiename) {
  return cookie.get(cookiename);
}

export function logOut() {
  if (getCookie('loggedInUser')) {
    cookie.remove('loggedInUser');
  }
}

export function loggedInUser() {
  return getCookie('loggedInUser');
}

import React, { useState, useEffect } from 'react';
import { AppTopbar } from '../app-layout/app-topbar';
import { AppFooter } from '../app-layout/app-footer';
import { AppMenu } from '../app-layout/app-menu';
import { Link } from 'react-router-dom';

const Main = (props) => {
  let layoutMode = 'static';
  let layoutColorMode = 'light';
  const [staticMenuInactive, setStaticMenuInactive] = useState(false);
  const [overlayMenuActive, setOverlayMenuActive] = useState(false);
  const [mobileMenuActive, setMobileMenuActive] = useState(false);
  const [mobileTopbarMenuActive, setMobileTopbarMenuActive] = useState(false);

  let menuClick = false;
  let mobileTopbarMenuClick = false;

  useEffect(() => {
    if (mobileMenuActive) {
      addClass(document.body, 'body-overflow-hidden');
    } else {
      removeClass(document.body, 'body-overflow-hidden');
    }
  }, [mobileMenuActive]);

  const onWrapperClick = (event) => {
    if (!menuClick) {
      setOverlayMenuActive(false);
      setMobileMenuActive(false);
    }

    if (!mobileTopbarMenuClick) {
      setMobileTopbarMenuActive(false);
    }

    mobileTopbarMenuClick = false;
    menuClick = false;
  };

  const onToggleMenuClick = (event) => {
    menuClick = true;

    if (isDesktop()) {
      if (layoutMode === 'overlay') {
        if (mobileMenuActive === true) {
          setOverlayMenuActive(true);
        }

        setOverlayMenuActive((prevState) => !prevState);
        setMobileMenuActive(false);
      } else if (layoutMode === 'static') {
        setStaticMenuInactive((prevState) => !prevState);
      }
    } else {
      setMobileMenuActive((prevState) => !prevState);
    }

    event.preventDefault();
  };

  const onSidebarClick = () => {
    menuClick = true;
  };

  const onMobileTopbarMenuClick = (event) => {
    mobileTopbarMenuClick = true;

    setMobileTopbarMenuActive((prevState) => !prevState);
    event.preventDefault();
  };

  const onMobileSubTopbarMenuClick = (event) => {
    mobileTopbarMenuClick = true;

    event.preventDefault();
  };

  const onMenuItemClick = (event) => {
    if (!event.item.items) {
      setOverlayMenuActive(false);
      setMobileMenuActive(false);
    }
  };
  const isDesktop = () => {
    return window.innerWidth >= 992;
  };

  const menu = [
    {
      label: 'Dashboard',
      icon: 'icon material-icons md-home',
      to: '/dashboard'
    }
  ];

  const addClass = (element, className) => {
    if (element.classList) element.classList.add(className);
    else element.className += ' ' + className;
  };

  const removeClass = (element, className) => {
    if (element.classList) element.classList.remove(className);
    else
      element.className = element.className.replace(
        new RegExp(
          '(^|\\b)' + className.split(' ').join('|') + '(\\b|$)',
          'gi'
        ),
        ' '
      );
  };
  return (
    <>
      <b className="screen-overlay"></b>
      <aside className="navbar-aside" id="offcanvas_aside">
        <div className="aside-top">
          <Link to="/" className="brand-wrap">
            <img
              src={`${process.env.PUBLIC_URL}/images/logo.png`}
              height="40"
              className="logo"
              alt="DFCU Bank Logo"
            />
          </Link>
          <div>
            <button className="btn btn-icon btn-aside-minimize">
              <i className="text-muted material-icons md-menu_open"></i>
            </button>
          </div>
        </div>
        <nav>
          <AppMenu
            model={menu}
            onMenuItemClick={onMenuItemClick}
            layoutColorMode={layoutColorMode}
          />
        </nav>
      </aside>

      <main className="main-wrap">
        <AppTopbar
          onToggleMenuClick={onToggleMenuClick}
          layoutColorMode={layoutColorMode}
          mobileTopbarMenuActive={mobileTopbarMenuActive}
          onMobileTopbarMenuClick={onMobileTopbarMenuClick}
          onMobileSubTopbarMenuClick={onMobileSubTopbarMenuClick}
        />
      </main>
    </>
  );
};

export default Main;

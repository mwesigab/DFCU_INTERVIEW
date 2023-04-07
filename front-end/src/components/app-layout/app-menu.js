import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const AppSubmenu = (props) => {
  const [activeIndex, setActiveIndex] = useState(null);

  const onMenuItemClick = (event, item, index) => {
    //avoid processing disabled items
    if (item.disabled) {
      event.preventDefault();
      return true;
    }

    //execute command
    if (item.command) {
      item.command({ originalEvent: event, item: item });
    }

    if (index === activeIndex) setActiveIndex(null);
    else setActiveIndex(index);

    if (props.onMenuItemClick) {
      props.onMenuItemClick({
        originalEvent: event,
        item: item
      });
    }
  };

  let items =
    props.items &&
    props.items.map((item, i) => {
      let active = activeIndex === i;

      if (item.to)
        return (
          <li className="menu-item" key={i}>
            <Link className="menu-link" to={item.to}>
              <i className="icon material-icons md-home"></i>
              <span className="text">{item.label}</span>
            </Link>
          </li>
        );
      else
        return (
          <li className="menu-item has-submenu" key={i}>
            <div
              className="menu-link"
              onClick={(e) => onMenuItemClick(e, item, i)}
            >
              <i className={item.icon}></i>
              <span className="text">{item.label}</span>
            </div>

            <div
              className="submenu"
              style={{ display: active ? 'block' : 'none' }}
            >
              {item.items &&
                item.items.map((item, index) => (
                  <Link key={index} to={item.to}>
                    {item.label}
                  </Link>
                ))}
            </div>
          </li>
        );
    });

  return items ? <ul className={props.className}>{items}</ul> : null;
};

export const AppMenu = (props) => {
  return (
    <AppSubmenu
      items={props.model}
      className="menu-aside"
      onMenuItemClick={props.onMenuItemClick}
      root={true}
    />
  );
};

import React from 'react';
import './App.scss';
import SideNav from './common/components/SideNav';
import ScreenContent from './common/components/ScreenContent';

function App() {
  const theme = 'light';

  return (
    <div className={`app app--${theme}`}>
      <SideNav className="side-nav" />
      <ScreenContent className="screen-content" />
    </div>
  );
}

export default App;

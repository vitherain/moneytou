import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

it('renders side menu and main body', () => {
  const renderResult = render(<App />);
  expect(renderResult.container.querySelector('div#side-menu')).toBeInTheDocument();
  expect(renderResult.container.querySelector('div#screen-content')).toBeInTheDocument();
});

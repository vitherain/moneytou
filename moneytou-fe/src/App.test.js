import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

describe('<App /> spec', () => {
  it('renders the component', () => {
    const { container } = render(<App />);
    expect(container).toMatchSnapshot();
  });

  it('renders side nav and main body', () => {
    const { container } = render(<App />);
    expect(container.querySelector('.app .side-nav')).toBeInTheDocument();
    expect(container.querySelector('.app .screen-content')).toBeInTheDocument();
  });
});

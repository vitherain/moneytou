import React from 'react';
import { render } from '@testing-library/react';
import App from './App';

describe('<App /> spec', () => {
  it('renders the component', () => {
    const { container } = render(<App />);
    expect(container).toMatchSnapshot();
  });

  it('renders side menu and main body', () => {
    const { container } = render(<App />);
    expect(container.querySelector('div#side-menu')).toBeInTheDocument();
    expect(container.querySelector('div#screen-content')).toBeInTheDocument();
  });
});

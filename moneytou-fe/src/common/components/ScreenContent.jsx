import React from 'react';
import { graphql, QueryRenderer } from 'react-relay';
import environment from '../../relay-env';

graphql`
    fragment ScreenContent_tx on Tx {
        id,
        accountId,
        date,
        amount {
            value,
            currency
        },
        categoryId,
        note
    }
`;

function ScreenContent() {
  return (
      <QueryRenderer
          environment={environment}
          query={graphql`
            query ScreenContentQuery($pageRequest: PageRequest!) {
              transactions(pageRequest: $pageRequest) {
                ...ScreenContent_tx
              }
            }
          `}
          variables={
            {
              pageRequest: {
                page: 0,
                size: 2
              }
            }
          }
          render={({ error, props }) => {
            if (props && props.data) {
              return <div>Máme {props.data.transactions.length} transakcí</div>;
            } else if (error) {
              return <div>{error.message}</div>;
            }

            return <div>Loading</div>;
          }}
      />
  );
}

export default ScreenContent;

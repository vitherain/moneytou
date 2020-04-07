/**
 * @flow
 * @relayHash 7f855da0389a3f28b5b41c3ec3c95e89
 */

/* eslint-disable */

'use strict';

/*::
import type { ConcreteRequest } from 'relay-runtime';
type ScreenContent_tx$ref = any;
export type PageRequest = {|
  page: number,
  size: number,
|};
export type ScreenContentQueryVariables = {|
  pageRequest: PageRequest
|};
export type ScreenContentQueryResponse = {|
  +transactions: $ReadOnlyArray<?{|
    +$fragmentRefs: ScreenContent_tx$ref
  |}>
|};
export type ScreenContentQuery = {|
  variables: ScreenContentQueryVariables,
  response: ScreenContentQueryResponse,
|};
*/


/*
query ScreenContentQuery(
  $pageRequest: PageRequest!
) {
  transactions(pageRequest: $pageRequest) {
    ...ScreenContent_tx
    id
  }
}

fragment ScreenContent_tx on Tx {
  id
  accountId
  date
  amount {
    value
    currency
  }
  categoryId
  note
}
*/

const node/*: ConcreteRequest*/ = (function(){
var v0 = [
  {
    "kind": "LocalArgument",
    "name": "pageRequest",
    "type": "PageRequest!",
    "defaultValue": null
  }
],
v1 = [
  {
    "kind": "Variable",
    "name": "pageRequest",
    "variableName": "pageRequest"
  }
];
return {
  "kind": "Request",
  "fragment": {
    "kind": "Fragment",
    "name": "ScreenContentQuery",
    "type": "Query",
    "metadata": null,
    "argumentDefinitions": (v0/*: any*/),
    "selections": [
      {
        "kind": "LinkedField",
        "alias": null,
        "name": "transactions",
        "storageKey": null,
        "args": (v1/*: any*/),
        "concreteType": "Tx",
        "plural": true,
        "selections": [
          {
            "kind": "FragmentSpread",
            "name": "ScreenContent_tx",
            "args": null
          }
        ]
      }
    ]
  },
  "operation": {
    "kind": "Operation",
    "name": "ScreenContentQuery",
    "argumentDefinitions": (v0/*: any*/),
    "selections": [
      {
        "kind": "LinkedField",
        "alias": null,
        "name": "transactions",
        "storageKey": null,
        "args": (v1/*: any*/),
        "concreteType": "Tx",
        "plural": true,
        "selections": [
          {
            "kind": "ScalarField",
            "alias": null,
            "name": "id",
            "args": null,
            "storageKey": null
          },
          {
            "kind": "ScalarField",
            "alias": null,
            "name": "accountId",
            "args": null,
            "storageKey": null
          },
          {
            "kind": "ScalarField",
            "alias": null,
            "name": "date",
            "args": null,
            "storageKey": null
          },
          {
            "kind": "LinkedField",
            "alias": null,
            "name": "amount",
            "storageKey": null,
            "args": null,
            "concreteType": "Money",
            "plural": false,
            "selections": [
              {
                "kind": "ScalarField",
                "alias": null,
                "name": "value",
                "args": null,
                "storageKey": null
              },
              {
                "kind": "ScalarField",
                "alias": null,
                "name": "currency",
                "args": null,
                "storageKey": null
              }
            ]
          },
          {
            "kind": "ScalarField",
            "alias": null,
            "name": "categoryId",
            "args": null,
            "storageKey": null
          },
          {
            "kind": "ScalarField",
            "alias": null,
            "name": "note",
            "args": null,
            "storageKey": null
          }
        ]
      }
    ]
  },
  "params": {
    "operationKind": "query",
    "name": "ScreenContentQuery",
    "id": null,
    "text": "query ScreenContentQuery(\n  $pageRequest: PageRequest!\n) {\n  transactions(pageRequest: $pageRequest) {\n    ...ScreenContent_tx\n    id\n  }\n}\n\nfragment ScreenContent_tx on Tx {\n  id\n  accountId\n  date\n  amount {\n    value\n    currency\n  }\n  categoryId\n  note\n}\n",
    "metadata": {}
  }
};
})();
// prettier-ignore
(node/*: any*/).hash = '187d8537f714bc163a993a6d161ad4e2';

module.exports = node;

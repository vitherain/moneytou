/**
 * @flow
 */

/* eslint-disable */

'use strict';

/*::
import type { ReaderFragment } from 'relay-runtime';
export type Currency = "CZK" | "EUR" | "USD" | "%future added value";
import type { FragmentReference } from "relay-runtime";
declare export opaque type ScreenContent_tx$ref: FragmentReference;
declare export opaque type ScreenContent_tx$fragmentType: ScreenContent_tx$ref;
export type ScreenContent_tx = {|
  +id: string,
  +accountId: string,
  +date: string,
  +amount: {|
    +value: number,
    +currency: Currency,
  |},
  +categoryId: string,
  +note: ?string,
  +$refType: ScreenContent_tx$ref,
|};
export type ScreenContent_tx$data = ScreenContent_tx;
export type ScreenContent_tx$key = {
  +$data?: ScreenContent_tx$data,
  +$fragmentRefs: ScreenContent_tx$ref,
  ...
};
*/


const node/*: ReaderFragment*/ = {
  "kind": "Fragment",
  "name": "ScreenContent_tx",
  "type": "Tx",
  "metadata": null,
  "argumentDefinitions": [],
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
};
// prettier-ignore
(node/*: any*/).hash = 'd716d97e1cbb6625eeb93bcd14a5aa78';

module.exports = node;

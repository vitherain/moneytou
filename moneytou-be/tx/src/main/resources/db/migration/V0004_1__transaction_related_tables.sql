CREATE TABLE moneytou.transaction(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    amount NUMERIC(19,2) NOT NULL,
    currency VARCHAR(20) NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    category_id UUID NOT NULL,
    account_id UUID NOT NULL,
    note TEXT NULL,

    CONSTRAINT transaction_pkey PRIMARY KEY (id),
    CONSTRAINT fk_transaction__account FOREIGN KEY (account_id)
        REFERENCES moneytou.account(id),
    CONSTRAINT chck_currency CHECK (currency IN ('CZK', 'EUR', 'USD'))
);

CREATE TABLE moneytou.tx_label(
    tx_id UUID NOT NULL,
    name VARCHAR(40) NOT NULL,

    CONSTRAINT tx_label_pkey PRIMARY KEY (tx_id, name),
    CONSTRAINT fk_tx_label__transaction FOREIGN KEY (tx_id)
        REFERENCES moneytou.transaction(id)
);

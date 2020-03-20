CREATE EXTENSION "uuid-ossp";
CREATE SCHEMA IF NOT EXISTS moneytou AUTHORIZATION moneytou_dba;

CREATE TABLE moneytou.account(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,

    CONSTRAINT account_pkey PRIMARY KEY (id)
);

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

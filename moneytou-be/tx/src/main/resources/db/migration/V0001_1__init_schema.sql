CREATE EXTENSION "uuid-ossp";
CREATE SCHEMA IF NOT EXISTS moneytou AUTHORIZATION moneytou_dba;

CREATE TABLE moneytou.transaction(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    amount NUMERIC(19,2) NOT NULL,
    currency VARCHAR(20) NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    category_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    note TEXT NULL,

    CONSTRAINT transaction_pkey PRIMARY KEY (id),
    CONSTRAINT chck_currency CHECK (currency IN ('CZK', 'EUR', 'USD'))
);

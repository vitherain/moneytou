CREATE EXTENSION "uuid-ossp";
CREATE SCHEMA IF NOT EXISTS moneytou AUTHORIZATION moneytou_dba;

CREATE TABLE moneytou.account(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    name VARCHAR(40) NOT NULL,
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

CREATE TABLE moneytou.tx_label(
    tx_id UUID NOT NULL,
    name VARCHAR(40) NOT NULL,

    CONSTRAINT tx_label_pkey PRIMARY KEY (tx_id, name),
    CONSTRAINT fk_tx_label__transaction FOREIGN KEY (tx_id)
        REFERENCES moneytou.transaction(id)
);

CREATE TABLE moneytou.category(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    name VARCHAR(40) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    parent_category_id UUID NULL,

    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT fk_category__parent_category FOREIGN KEY (parent_category_id)
        REFERENCES moneytou.category(id),
    CONSTRAINT chck_icon CHECK (icon IN (
        'PIGGY_BANK',
        'UNIVERSITY',
        'MONEY_BILL',
        'EURO_SIGN',
        'DOLLAR_SIGN',
        'COINS',
        'CREDIT_CARD'
    ))
);
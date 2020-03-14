CREATE SCHEMA IF NOT EXISTS moneytou AUTHORIZATION moneytou_dba;

CREATE SEQUENCE moneytou.sq_transaction
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE moneytou.transaction(
    id UUID NOT NULL DEFAULT nextval('moneytou.sq_transaction')
);

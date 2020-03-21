CREATE TABLE moneytou.account(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    name VARCHAR(40) NOT NULL,
    user_id UUID NOT NULL,

    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT fk_category__user FOREIGN KEY (user_id)
        REFERENCES moneytou.user(id)
);

CREATE TABLE moneytou.category(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    name VARCHAR(40) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    parent_category_id UUID NULL,
    user_id UUID NOT NULL,

    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT fk_category__parent_category FOREIGN KEY (parent_category_id)
        REFERENCES moneytou.category(id),
    CONSTRAINT fk_category__user FOREIGN KEY (user_id)
        REFERENCES moneytou.user(id),
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

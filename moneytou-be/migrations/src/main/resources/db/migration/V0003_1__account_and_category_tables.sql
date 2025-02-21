CREATE TABLE moneytou.account(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    name VARCHAR(40) NOT NULL,
    status VARCHAR(8) NOT NULL,
    user_id UUID NOT NULL,

    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT fk_category__user FOREIGN KEY (user_id)
        REFERENCES moneytou.user(id),
    CONSTRAINT chck_status CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

CREATE TABLE moneytou.category(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    code VARCHAR(40) NOT NULL,
    status VARCHAR(8) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    parent_category_id UUID NULL,

    CONSTRAINT category_pkey PRIMARY KEY (id),
    CONSTRAINT fk_category__parent_category FOREIGN KEY (parent_category_id)
        REFERENCES moneytou.category(id),
    CONSTRAINT uk_code UNIQUE(code),
    CONSTRAINT chck_status CHECK (status IN ('ACTIVE', 'INACTIVE')),
    CONSTRAINT chck_icon CHECK (icon IN (
        'EXCHANGE_ALT',
        'PIGGY_BANK',
        'UNIVERSITY',
        'MONEY_BILL',
        'EURO_SIGN',
        'DOLLAR_SIGN',
        'COINS',
        'CREDIT_CARD'
    ))
);

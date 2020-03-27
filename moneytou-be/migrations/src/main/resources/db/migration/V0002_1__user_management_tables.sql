CREATE TABLE moneytou.user(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    username VARCHAR(100) NOT NULL,

    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE moneytou.user_role(
    user_id UUID NOT NULL,
    role VARCHAR(255) NOT NULL,

    CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role),
    CONSTRAINT fk_user_role__user FOREIGN KEY (user_id)
        REFERENCES moneytou.user(id),
    CONSTRAINT chck_role CHECK (role IN (
        'ROLE_NON_CONFIRMED_USER',
        'ROLE_FREE_USER',
        'ROLE_PAID_USER'
    ))
);

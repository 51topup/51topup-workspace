create table account
(
    id              bigserial primary key,
    mobile_phone    varchar(32)  not null,
    hashed_password varchar(100) not null,
    first_name      varchar(100) not null,
    last_name       varchar(100) not null,
    role_names      varchar(100) not null,
    type            int          not null, -- 0: seller, 1: admin, 2: service provider
    status          int          not null,
    flag            bigint,
    created_at      timestamp    not null,
    updated_at      timestamp    not null
);

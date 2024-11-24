create table account
(
    id              bigserial primary key,
    nick            varchar(100) not null,
    mobile_phone    varchar(32)  not null,
    email           varchar(128),
    hashed_password varchar(100) not null,
    first_name      varchar(100) not null,
    last_name       varchar(100) not null,
    roles           varchar(100) not null,
    type            int          not null, -- 0: seller, 1: admin, 2: service provider
    status          int          not null,
    flag            bigint,
    created_at      timestamp    not null,
    updated_at      timestamp    not null
);

create table goofish_profile
(
    id                   bigserial primary key,
    account_id           bigint       not null,
    taobao_user_id       varchar(100) not null,
    taobao_user_nick     varchar(100) not null,
    taobao_open_uid      varchar(100) not null,
    sub_taobao_user_nick varchar(100) not null,
    sub_taobao_user_id   varchar(100) not null,
    access_token         varchar(100) not null,
    expires_in           timestamp    not null,
    refresh_token        varchar(100) not null,
    re_expires_in        timestamp    not null,
    status               int          not null, -- 0: normal
    created_at           timestamp    not null,
    updated_at           timestamp    not null
);

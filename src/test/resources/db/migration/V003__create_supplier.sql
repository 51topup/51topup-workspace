-- 供货商表
create table supplier
(
    id           bigserial primary key,
    avatar       varchar(256),
    name         varchar(100) not null,
    description  text,
    url          varchar(256),
    api_url      varchar(256),
    submitted_by bigint       not null,
    status       int          not null,
    created_at   timestamp    not null,
    updated_at   timestamp    not null
);

-- 供货商商品表
create table supplier_goods
(
    id          bigserial primary key,
    supplier_id bigint       not null,
    title       varchar(100) not null,
    main_pic    varchar(256),
    description text,
    price       int          not null,
    status      int          not null,
    created_at  timestamp    not null,
    updated_at  timestamp    not null
);

-- 供货商订单表
create table supplier_order
(
    id                bigserial primary key,
    supplier_id       bigint       not null,
    seller_id         bigint       not null,
    supplier_order_id varchar(100) not null,
    platform_order_id bigint       not null,
    item_id           bigint       not null,
    item_price        int          not null,
    buy_amount        int          not null,
    payment           int          not null,
    pay_time          timestamp,
    buyer_nick        varchar(64)  not null,
    status            int          not null,
    gmt_create        timestamp    not null,
    gmt_modified      timestamp    not null
);

-- 供货商的账户表
create table supplier_account
(
    id          bigserial primary key,
    supplier_id bigint       not null,
    seller_id   bigint       not null,
    username    varchar(100) not null,
    passwd      varchar(100) not null,
    token       varchar(100) not null,
    balance     int          not null,
    status      int          not null,
    created_at  timestamp    not null,
    updated_at  timestamp    not null
);
create table goo_fish_order
(
    id               bigserial primary key,
    account_id       bigint       not null,
    order_id         bigint       not null,
    item_id          bigint       not null,
    item_pic_url     varchar(256) not null,
    item_title       varchar(256) not null,
    item_price       int          not null,
    buy_amount       int          not null,
    payment          int          not null,
    post_fee         int          not null,
    pay_time         timestamp,
    ship_time        timestamp,
    buyer_nick       varchar(64)  not null,
    seller_nick      varchar(64)  not null,
    status           int          not null,
    logistics_status int          not null,
    gmt_create       timestamp    not null,
    gmt_modified     timestamp    not null
);

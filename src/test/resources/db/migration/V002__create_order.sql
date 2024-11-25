-- 闲鱼订单
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

-- 闲鱼订单评价
create table goo_fish_order_rate
(
    id            bigserial primary key,
    account_id    bigint    not null,
    order_id      bigint    not null,
    rate          int,          -- 评价结果 1 好评 0 默认
    feedback      varchar(256),
    rate_type     int,          -- 评价类型 1 好评 0 中评
    reviewer_nick varchar(100), -- 评价人昵称
    reviewer_type int,          -- 评价人类型 1 买家 0 卖家
    gmt_create    timestamp not null,
    gmt_modified  timestamp not null
);
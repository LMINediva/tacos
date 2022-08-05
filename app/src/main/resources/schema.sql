drop table ingredient;
drop table taco;
drop table taco_ingredients;
drop table taco_order;
drop table taco_order_tacos;
drop table user;

create table if not exists ingredient
(
    id   varchar(4)  not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists taco
(
    id         bigint(30)  not null primary key auto_increment,
    name       varchar(50) not null,
    created_at timestamp   not null
);

create table if not exists taco_ingredients
(
    taco_id        bigint(30) not null,
    ingredients_id varchar(4) not null
);

alter table taco_ingredients
    add foreign key (taco_id) references taco (id);

alter table taco_ingredients
    add foreign key (ingredients_id) references ingredient (id);

create table if not exists taco_order
(
    id              bigint(30)  not null primary key auto_increment,
    delivery_name   varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city   varchar(50) not null,
    delivery_state  varchar(30)  not null,
    delivery_zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cccvv           varchar(3)  not null,
    placed_at       timestamp   not null,
    user_id         bigint(30)
);

create table if not exists taco_order_tacos
(
    order_id bigint(30) not null,
    tacos_id      bigint(30) not null
);

alter table taco_order_tacos
    add foreign key (order_id) references taco_order (id);

alter table taco_order_tacos
    add foreign key (tacos_id) references taco (id);

create table if not exists user
(
    id           bigint(30)   not null primary key auto_increment,
    username     varchar(30)  not null,
    password     varchar(500) not null,
    fullname     varchar(30),
    street       varchar(30),
    city         varchar(30),
    state        varchar(30),
    zip          varchar(30),
    phone_number varchar(30)
);

create table if not exists taco_order_users
(
    taco_order_id bigint(30) not null,
    user_id       bigint(30) not null
);

alter table taco_order_users
    add foreign key (taco_order_id) references taco_order (id);
alter table taco_order_users
    add foreign key (user_id) references user (id);
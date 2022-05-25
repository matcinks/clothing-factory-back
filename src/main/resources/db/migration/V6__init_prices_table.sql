drop table if exists prices;

create table prices
(
    price_id   bigint primary key not null,
    price      decimal(19, 2)     not null,
    valid_from timestamp,
    product_id bigint
);

alter table prices
    add foreign key (product_id) references products (id);
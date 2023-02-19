
create table if not exists products_changes
(
    change_id    bigserial primary key,
    product_id   bigint       not null,
    property_name varchar(255) not null,
    value_before varchar(255) not null,
    value_after  varchar(255) not null,
    change_date  timestamp
);

alter table products_changes
    add foreign key (product_id) references products (id);
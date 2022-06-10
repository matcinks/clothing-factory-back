drop table if exists products_sizes;

create table products_sizes
(
    product_id bigint not null,
    size_id    bigint not null
);

alter table products_sizes
    add foreign key (product_id) references products (id);

alter table products_sizes
    add foreign key (size_id) references sizes (id);
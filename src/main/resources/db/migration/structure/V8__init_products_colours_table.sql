
create table if not exists products_colours
(
    product_id bigint not null,
    colour_id  bigint not null
);

alter table products_colours
    add foreign key (product_id) references products (id);

alter table products_colours
    add foreign key (colour_id) references colours (id);
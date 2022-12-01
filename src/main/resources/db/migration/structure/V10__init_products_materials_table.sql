drop table if exists products_materials;

create table products_materials
(
    product_id bigint not null,
    material_id    bigint not null
);

alter table products_materials
    add foreign key (product_id) references products (id);

alter table products_materials
    add foreign key (material_id) references materials (id);
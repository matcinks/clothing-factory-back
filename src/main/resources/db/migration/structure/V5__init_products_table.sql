drop table if exists products;

create table products
(
    id                     bigint primary key auto_increment,
    name                   varchar(255)       not null,
    description            varchar(255)       not null,
    additional_information varchar(255),
    category               varchar(255)       not null,
    material_id            bigint             not null,
    material_usage         double,
    unit_usage             varchar(255)
);

alter table products
    add foreign key (material_id) references materials (id);
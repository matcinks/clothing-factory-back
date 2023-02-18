drop table if exists materials;

create table materials
(
--     id                     bigint primary key auto_increment,
    id                     bigserial primary key,
    name                   varchar(255)   not null,
    additional_description varchar(255),
    price                  decimal(19, 2) not null,
    price_unit             varchar(255),
    created_at             timestamp
);
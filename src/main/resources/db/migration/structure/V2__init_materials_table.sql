
create table if not exists materials
(
    id                     bigserial primary key,
    name                   varchar(255)   not null,
    additional_description varchar(255),
    price                  decimal(19, 2) not null,
    price_unit             varchar(255),
    created_at             timestamp
);
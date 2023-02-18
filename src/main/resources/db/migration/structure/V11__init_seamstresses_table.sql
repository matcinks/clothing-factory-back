drop table if exists seamstresses;

create table seamstresses
(
--     id   bigint primary key auto_increment,
    id   bigserial primary key,
    name varchar(255)       not null
);
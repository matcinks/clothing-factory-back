drop table if exists colours;

create table colours
(
--     id         bigint primary key auto_increment,
    id         bigserial primary key,
    name       varchar(255)       not null unique,
    created_at timestamp
);
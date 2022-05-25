drop table if exists sizes;

create table sizes
(
    id         bigint primary key not null,
    name       varchar(255)       not null,
    created_at timestamp
);
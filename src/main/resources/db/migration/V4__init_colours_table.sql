drop table if exists colours;

create table colours
(
    id         bigint primary key not null,
    name       varchar(255)       not null,
    created_at timestamp
);
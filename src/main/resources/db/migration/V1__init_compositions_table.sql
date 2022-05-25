drop table if exists compositions;

create table compositions
(
    id           bigint primary key not null,
    name         varchar(255)       not null,
    raw_material varchar(255)       not null,
    percentage   double             not null
);
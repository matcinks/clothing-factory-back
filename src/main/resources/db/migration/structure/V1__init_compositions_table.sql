drop table if exists compositions;

create table compositions
(
--     id           bigint primary key auto_increment,
    id           bigserial primary key,
    raw_material varchar(255)   not null,
    percentage   int not null
);
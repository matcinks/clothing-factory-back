drop table if exists knitting_devices;

create table knitting_devices
(
    id           bigint primary key not null,
    gauge        varchar(255)       not null,
    heads_amount integer            not null
);
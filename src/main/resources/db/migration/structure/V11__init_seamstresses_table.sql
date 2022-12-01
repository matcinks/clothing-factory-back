drop table if exists seamstresses;

create table seamstresses
(
    id   bigint primary key auto_increment,
    name varchar(255)       not null
);
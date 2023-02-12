drop table if exists users;

create table users
(
    id         bigint primary key auto_increment,
    name       varchar(255) unique,
    password varchar(255),
    role varchar(255)

);
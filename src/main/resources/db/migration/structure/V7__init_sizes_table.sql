
create table if not exists sizes
(
    id         bigserial primary key,
    name       varchar(255) not null unique,
    created_at timestamp
);

create table if not exists compositions
(
    id           bigserial primary key,
    raw_material varchar(255)   not null,
    percentage   int not null
);
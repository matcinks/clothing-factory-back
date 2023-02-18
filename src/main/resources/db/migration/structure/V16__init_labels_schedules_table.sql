drop table if exists labels_schedules;

create table labels_schedules
(
--     id           bigint primary key auto_increment,
    id           bigserial primary key,
    product_id   bigint       not null,
    colour_id    bigint       not null,
    size_id      bigint       not null,
    amount       integer      not null,
    status       varchar(255) not null,
    priority     integer      not null,
    labels_type  varchar(255) not null,
    scheduled_on timestamp
);

alter table labels_schedules
    add foreign key (product_id) references products (id);

alter table labels_schedules
    add foreign key (colour_id) references colours (id);

alter table labels_schedules
    add foreign key (size_id) references sizes (id);
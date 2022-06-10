drop table if exists knitting_schedules;

create table knitting_schedules
(
    id                 bigint primary key not null,
    product_id         bigint             not null,
    knitting_device_id bigint             not null,
    colour_id          bigint             not null,
    size_id            bigint             not null,
    amount             integer            not null,
    status             varchar(255)       not null,
    priority           integer            not null,
    scheduled_on       timestamp
);

alter table knitting_schedules
    add foreign key (product_id) references products (id);

alter table knitting_schedules
    add foreign key (knitting_device_id) references knitting_devices (id);

alter table knitting_schedules
    add foreign key (colour_id) references colours (id);
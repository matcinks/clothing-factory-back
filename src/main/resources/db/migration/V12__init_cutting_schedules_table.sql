drop table if exists cutting_schedules;

create table cutting_schedules
(
    id            bigint primary key not null,
    product_id    bigint             not null,
    colour_id     bigint             not null,
    size_id       bigint             not null,
    amount        integer            not null,
    status        varchar(255)       not null,
    priority      integer            not null,
    scheduled_on  timestamp
);

alter table cutting_schedules
    add foreign key (product_id) references products (id);

alter table cutting_schedules
    add foreign key (colour_id) references colours (id);

alter table cutting_schedules
    add foreign key (size_id) references sizes (id);
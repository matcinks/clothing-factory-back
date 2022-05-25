drop table if exists labels_schedules;

create table labels_schedules
(
    id                    bigint primary key not null,
    product_id            bigint             not null,
    washing_labels_amount integer,
    washing_labels_status varchar(255)       not null,
    price_labels_amount   integer,
    price_labels_status   varchar(255)       not null,
    scheduled_on          timestamp
);

alter table labels_schedules
    add foreign key (product_id) references products (id);
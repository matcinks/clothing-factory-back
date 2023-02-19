
create table if not exists materials_compositions
(
    material_id    bigint not null,
    composition_id bigint not null
);

alter table materials_compositions
    add foreign key (material_id) references materials (id);

alter table materials_compositions
    add foreign key (composition_id) references compositions (id);
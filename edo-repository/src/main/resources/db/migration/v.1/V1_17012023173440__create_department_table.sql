create table if not exists department
(
    id            bigserial not null references address (id),
    short_name    text,                           --Короткое имя, возможно аббревиатура
    full_name     text,                           --Полное имя
    address_id    bigint    not null primary key, --ключ адреса
    archived_date timestamp(6),                   --дата закрытия
    creation_date timestamp(6),                   --дата открытия
    external_id   bigint,                         --ключ внешней таблицы
    phone         varchar(255),                   --номер телефона
    department_id bigint                          --ключ вышестоящего отдела
);

alter table if exists department
    add constraint fk_uq1_address_department foreign key (address_id) references address;

alter table if exists department
    add constraint jk_uq1_department_department foreign key (department_id) references department;

comment on column department.id is 'id';
comment on column department.short_name is 'аббревиатура';
comment on column department.full_name is 'Полное имя';
comment on column department.address_id is 'ключ адреса';
comment on column department.archived_date is 'дата закрытия';
comment on column department.creation_date is 'дата основания';
comment on column department.external_id is 'ключ внешней таблицы';
comment on column department.phone is 'номер телефона';
comment on column department.department_id is 'ключ вышестоящего отдела';

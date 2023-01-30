create table if not exists department
(
    id            bigserial primary key,
    short_name    text,                                               --Короткое имя, возможно аббревиатура
    full_name     text,                                               --Полное имя
    address_id    bigint references address (id),                     --ключ адреса
    archived_date timestamp with time zone default current_timestamp, --дата закрытия
    creation_date timestamp with time zone default current_timestamp, --дата открытия
    external_id   bigint,                                             --ключ внешней таблицы
    phone         varchar(255),                                       --номер телефона
    department_id bigint references department (id)                   --ключ вышестоящего отдела
);

comment on column department.id is 'id';
comment on column department.short_name is 'аббревиатура';
comment on column department.full_name is 'Полное имя';
comment on column department.address_id is 'ключ адреса';
comment on column department.archived_date is 'дата закрытия';
comment on column department.creation_date is 'дата основания';
comment on column department.external_id is 'ключ внешней таблицы';
comment on column department.phone is 'номер телефона';
comment on column department.department_id is 'ключ вышестоящего отдела';

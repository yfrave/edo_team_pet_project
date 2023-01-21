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
create table if not exists edo.department
(
    id            bigserial not null primary key,    --id
    short_name    text,                              --Короткое имя, возможно аббревиатура
    full_name     text,
    address       bigserial REFERENCES address (id), --Адрес, сущность.
    need_todo     text,
    phone         text,
    department_id bigserial REFERENCES department (id),
    creation_date timestamp with time zone,          --Дата создания номенклатуры
    archived_date timestamp with time zone           --Дата перевода в архив
)
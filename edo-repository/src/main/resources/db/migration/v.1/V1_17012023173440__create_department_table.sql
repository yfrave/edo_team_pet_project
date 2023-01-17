create table if not exists edo.department
(
    id                 bigserial not null primary key, --id
    short_name         text,                           --Короткое имя, возможно аббревиатура
    full_name          text,
    department_address text,
    need_todo text,
    phone text,
    not_done text,
    creation_date      timestamp with time zone,       --Дата создания номенклатуры
    archived_date      timestamp with time zone       --Дата перевода в архив
)
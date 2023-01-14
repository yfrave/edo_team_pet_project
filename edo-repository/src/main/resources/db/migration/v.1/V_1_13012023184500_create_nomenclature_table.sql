create table if not exists nomenclature
(
    id              bigserial not null primary key,
    creation_date   timestamp with timezone,
    archived_date   timestamp with timezone,
    template        varchar,
    current_value   bigint,
    index           varchar
);

comment on column nomenclature.id is 'id';
comment on column nomenclature.creation_date is 'Дата создания номенклатуры';
comment on column nomenclature.archived_date is 'Дата перевода в архив';
comment on column nomenclature.template is 'Шаблон';
comment on column nomenclature.current_value is 'Текущее значение';
comment on column nomenclature.index is 'Индекс';
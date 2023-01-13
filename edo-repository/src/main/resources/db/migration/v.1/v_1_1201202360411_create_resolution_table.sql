create table if not exists resolution
(
    id               bigserial not null primary key,
    creation_date    date,
    archived_date    date,
    last_action_date date,
    resolution_name  varchar(30),
    creator          bigserial,
    signer           bigserial,
    executor         bigserial,
    curator          bigserial
);

comment on column resolution.id is 'id';
comment on column resolution.creation_date is 'Дата создания резолюции';
comment on column resolution.archived_date  is 'Дата архивации';
comment on column resolution.last_action_date is 'Дата последнего события';
comment on column resolution.resolution_name is 'Переменная отражающая тип решения';
comment on column resolution.creator is 'id Работника создавший резолюцию';
comment on column resolution.signer is 'id Работника подписывающий документ';
comment on column resolution.executor is 'id Работников выполняющие решение';
comment on column resolution.curator is 'id Работника курирующий работу';


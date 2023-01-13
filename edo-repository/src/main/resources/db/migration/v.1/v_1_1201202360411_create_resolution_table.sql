create table if not exists resolution
(
    id               bigserial not null primary key,
    creation_date    date,
    archived_date    date,
    last_action_date date,
    resolution_name  varchar,
    creator_id       bigserial,
    signer_id        bigserial,
    executor_id      bigserial,
    curator_id       bigserial
);

comment on column resolution.id is 'id';
comment on column resolution.creation_date is 'Дата создания резолюции';
comment on column resolution.archived_date is 'Дата архивации';
comment on column resolution.last_action_date is 'Дата последнего события';
comment on column resolution.resolution_name is 'Переменная отражающая тип решения';
comment on column resolution.creator_id is 'id Работника создавший резолюцию';
comment on column resolution.signer_id is 'id Работника подписывающий документ';
comment on column resolution.executor_id is 'id Работников выполняющие решение';
comment on column resolution.curator_id is 'id Работника курирующий работу';


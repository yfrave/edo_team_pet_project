create table if not exists appeal
(
    id              bigserial    not null primary key,                  -- id
    creation_date   timestamptz  not null,                              -- Дата создания обращения
    archived_date   timestamptz,                                        -- Дата архивирования обращения
    number          varchar(255) not null,                              -- Номер обращения
    annotation      varchar(255) not null,                              -- Описание обращения
    creator_id      bigint       not null references employee (id),     -- Автор
    nomenclature_id bigint       not null references nomenclature (id), -- Номенклатура
    resolution_id   bigint       not null references resolution (id),   -- Резолюция
    theme_id        bigint       not null references theme (id),        -- Тема
    appeal_status   varchar,                                            -- Статус
    way_to_receive  varchar                                             -- Способ получения
);--Таблица описывающая обращение

create table if not exists appeal_signer
(
    appeal_id   bigint references appeal (id),
    employee_id bigint references employee (id)
); --Таблица one to many appeal_signer

create table if not exists appeal_addressee
(
    appeal_id   bigint references appeal (id),
    employee_id bigint references employee (id)
); --Таблица one to many appeal_addressee

create table if not exists appeal_author
(
    appeal_id bigint references appeal (id),
    author_id bigint references author (id)
); --Таблица one to many appeal_author

create table if not exists appeal_file_pool
(
    appeal_id    bigint references appeal (id),
    file_pool_id bigint references file_pool (id)
); --Таблица one to many appeal_file_pool

create table if not exists appeal_question
(
    appeal_id   bigint references appeal (id),
    question_id bigint references question (id)
); --Таблица one to many appeal_question

comment on column appeal_signer.appeal_id is 'id appeal';
comment on column appeal_signer.employee_id is 'id employee';

comment on column appeal_addressee.appeal_id is 'id appeal';
comment on column appeal_addressee.employee_id is 'id employee';

comment on column appeal_author.appeal_id is 'id appeal';
comment on column appeal_author.author_id is 'id author';

comment on column appeal_file_pool.appeal_id is 'id appeal';
comment on column appeal_file_pool.file_pool_id is 'id file_pool';

comment on column appeal_question.appeal_id is 'id appeal';
comment on column appeal_question.question_id is 'id question';

comment on column appeal.id is 'id';
comment on column appeal.creation_date is 'Дата создания обращения';
comment on column appeal.archived_date is 'Дата архивирования обращения';
comment on column appeal.number is 'Номер обращения';
comment on column appeal.annotation is 'Описание обращения';
comment on column appeal.creator_id is 'Автор';
comment on column appeal.nomenclature_id is 'Номенклатура';
comment on column appeal.resolution_id is 'Резолюция';
comment on column appeal.theme_id is 'Тема';
comment on column appeal.appeal_status is 'Статус';
comment on column appeal.way_to_receive is 'Способ получения';


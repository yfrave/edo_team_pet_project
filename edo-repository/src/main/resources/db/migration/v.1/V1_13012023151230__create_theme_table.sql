create table if not exists theme
(
    id bigserial not null primary key,
    theme_name varchar(255),            -- название темы
    creation_date date,                 -- дата создания темы
    archived_date date,                 -- дата архивации темы
    code varchar(255),                  -- код темы
    parent_id bigint                    -- родительская тема
);
comment on column theme.id is 'id темы обращения';
comment on column theme.theme_name is 'Название темы обращения';
comment on column theme.creation_date is 'Дата создания темы';
comment on column theme.archived_date is 'Дата архивации темы';
comment on column theme.code is 'Код темы';
comment on column theme.parent_id is 'id родительской темы';
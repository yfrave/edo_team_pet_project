create table if not exists question
(
    id bigserial not null primary key,   -- id
    creation_date date not null,         -- дата создания обращения
    archived_date date not null,         -- дата отклика на обращение
    summary varchar(255) not null        -- краткое содержание обращения
);
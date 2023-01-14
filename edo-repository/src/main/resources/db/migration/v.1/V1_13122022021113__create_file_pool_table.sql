CREATE TABLE if not exists file_pool
(   id bigserial not null primary key,
    storage_file_id UUID not null,
    file_name varchar(255),
    extension varchar(255),
    file_size int,
    page_count int,
    upload_date timestamp with timezone,
    archived_date timestamp with timezone,
    creator_id bigint
);
comment on column file_pool.id is 'ID'
comment on column file_pool.storage_file_id is 'ID хранилища файла';
comment on column file_pool.file_name is 'Имя хранилища файла';
comment on column file_pool.extension is 'Расширение файла';
comment on column file_pool.size is 'Размер файла';
comment on column file_pool.page_count is 'Количество страниц файла';
comment on column file_pool.upload_date is 'Дата загрузки файла';
comment on column file_pool.archived_date is 'Дата архивирования файла';
comment on column file_pool.creator_id is 'Автор файла';

ALTER TABLE IF EXISTS file_pool
    DROP COLUMN creator_id,
    ADD COLUMN IF NOT EXISTS creator_id bigint references employee (id); --Автор файла


comment on column file_pool.creator_id is 'Автор файла';

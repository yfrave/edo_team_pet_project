ALTER TABLE IF EXISTS appeal
    ADD COLUMN IF NOT EXISTS way_to_receive VARCHAR(20),
    ADD COLUMN IF NOT EXISTS appeal_status VARCHAR(20),
    ADD COLUMN IF NOT EXISTS nomenclature_id BIGINT REFERENCES nomenclature (id);

ALTER TABLE IF EXISTS question
    ADD COLUMN IF NOT EXISTS resolution_id BIGINT REFERENCES resolution (id),
    ADD COLUMN IF NOT EXISTS theme_id BIGINT REFERENCES theme (id);

CREATE TABLE IF NOT EXISTS appeal_author (
    appeal_id BIGINT REFERENCES appeal (id),
    author_id BIGINT references author (id)
);

CREATE TABLE IF NOT EXISTS appeal_file_pool (
    appeal_id BIGINT REFERENCES appeal (id),
    file_pool_id BIGINT REFERENCES file_pool (id)
);

CREATE TABLE IF NOT EXISTS appeal_question (
    appeal_id BIGINT REFERENCES appeal (id),
    question_id BIGINT REFERENCES question (id)
);

COMMENT ON COLUMN appeal.way_to_receive IS 'способ получения обращения';
COMMENT ON COLUMN appeal.appeal_status IS 'статус обращения';
COMMENT ON COLUMN appeal.nomenclature_id IS 'id номенклатуры обращения';
COMMENT ON COLUMN appeal_author.appeal_id IS 'id обращения';
COMMENT ON COLUMN appeal_author.author_id IS 'id автора';
COMMENT ON COLUMN appeal_file_pool.appeal_id IS 'id обращения';
COMMENT ON COLUMN appeal_file_pool.file_pool_id IS 'id хранилища файлов';
COMMENT ON COLUMN appeal_question.appeal_id IS 'id обращения';
COMMENT ON COLUMN appeal_question.question_id IS 'id вопроса';
COMMENT ON COLUMN question.resolution_id IS 'id объекта resolution, связанного с вопросом';
COMMENT ON COLUMN question.theme_id IS 'id темы вопроса';



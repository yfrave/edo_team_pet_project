ALTER TABLE IF EXISTS appeal
    ADD COLUMN IF NOT EXISTS way_to_receive VARCHAR(20),
    ADD COLUMN IF NOT EXISTS appeal_status VARCHAR(20),
    ADD COLUMN IF NOT EXISTS nomenclature_id BIGSERIAL REFERENCES nomenclature (id);

ALTER TABLE IF EXISTS question
    ADD COLUMN IF NOT EXISTS resolution_id BIGSERIAL REFERENCES resolution (id),
    ADD COLUMN IF NOT EXISTS theme_id BIGSERIAL REFERENCES theme (id);

CREATE TABLE IF NOT EXISTS appeal_author (
    appeal_id BIGSERIAL REFERENCES appeal (id),
    author_id bigint references author (id)
);

CREATE TABLE IF NOT EXISTS appeal_file_pool (
    appeal_id BIGSERIAL REFERENCES appeal (id),
    file_pool_id BIGSERIAL REFERENCES file_pool (id)
);

CREATE TABLE IF NOT EXISTS appeal_question (
    appeal_id BIGSERIAL REFERENCES appeal (id),
    question_id BIGSERIAL REFERENCES question (id)
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



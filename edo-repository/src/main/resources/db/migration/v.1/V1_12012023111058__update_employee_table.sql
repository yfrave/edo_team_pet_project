ALTER TABLE IF EXISTS employee
    ADD COLUMN IF NOT EXISTS first_name VARCHAR(20),
    ADD COLUMN last_name VARCHAR(20),
    ADD COLUMN middle_name VARCHAR(20),
    ADD COLUMN address VARCHAR(200),
    ADD COLUMN photo_url VARCHAR(300),
    ADD COLUMN fio_dative VARCHAR(60),
    ADD COLUMN fio_nominative VARCHAR(60),
    ADD COLUMN fio_genitive VARCHAR(60),
    ADD COLUMN external_id VARCHAR(20),
    ADD COLUMN phone VARCHAR(20),
    ADD COLUMN work_phone VARCHAR(20),
    ADD COLUMN birth_date TIMESTAMPTZ,
    ADD COLUMN username VARCHAR(20),
    ADD COLUMN creation_date TIMESTAMPTZ,
    ADD COLUMN archived_date TIMESTAMPTZ;

COMMENT ON COLUMN edo.employee.id IS 'Id работника';
COMMENT ON COLUMN edo.employee.first_name IS 'имя работника';
COMMENT ON COLUMN edo.employee.last_name IS 'фамилия работника';
COMMENT ON COLUMN edo.employee.middle_name IS 'отчество работника';
COMMENT ON COLUMN edo.employee.address IS 'адрес работника';
COMMENT ON COLUMN edo.employee.photo_url IS 'URL фото работника';
COMMENT ON COLUMN edo.employee.fio_dative IS 'ФИО в дательном падеже';
COMMENT ON COLUMN edo.employee.fio_nominative IS 'ФИО в именительном падеже';
COMMENT ON COLUMN edo.employee.fio_genitive IS 'ФИО в родительном падеже';
COMMENT ON COLUMN edo.employee.external_id IS 'Внешний идентификатор';
COMMENT ON COLUMN edo.employee.phone IS 'номер телефона сотовый';
COMMENT ON COLUMN edo.employee.work_phone IS 'номер телефона рабочий';
COMMENT ON COLUMN edo.employee.birth_date IS 'дата рождения';
COMMENT ON COLUMN edo.employee.username IS 'Имя пользователя';
COMMENT ON COLUMN edo.employee.creation_date IS 'дата создания';
COMMENT ON COLUMN edo.employee.archived_date IS 'дата архивации';
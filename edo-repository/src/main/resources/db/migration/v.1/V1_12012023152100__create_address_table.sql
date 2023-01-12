create table if not exists address(
    id bigserial not null primary key,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender CHAR(1),
    birthdate DATE,
    email_id VARCHAR(100) UNIQUE,
    country_of_birth VARCHAR(50)
);
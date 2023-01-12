create table if not exists edo.address(
    id bigserial not null primary key,
    full_address text,
    street text,
    house text,
    index text,
    housing text,
    building text,
    city text,
    region text,
    country text,
    flat text
);
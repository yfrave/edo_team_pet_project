create table if not exists address(
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

comment on column address.id is 'Это id, чего ещё ты ожидаешь здесь увидеть?';
comment on column address.full_address is 'Полный адрес';
comment on column address.street is 'Улица';
comment on column address.house is 'Номер дома';
comment on column address.index is 'Индекс';
comment on column address.housing is 'Корпус';
comment on column address.building is 'Строение';
comment on column address.city is 'Город';
comment on column address.region is 'Регион';
comment on column address.country is 'Странна';
comment on column address.flat is 'Этаж или номер квартиры';


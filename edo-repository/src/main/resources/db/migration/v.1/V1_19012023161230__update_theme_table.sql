--Добавление данных в таблицу theme
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (1, 'Вопросы по благоустройству', null, null, '1', null);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (2, 'благоустройство парков', null, null, '1.1', 1);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (3, 'благоустройство улиц', null, null, '1.2', 1);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (4, 'пешеходная часть', null, null, '1.2.2ю', 3);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (5, 'автомобильная часть', null, null, '1.2.2ю', 3);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (6, 'благоустройство дворов', null, null, '1.3', 1);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (7, 'Вопросы по работе коммунальных служб', null, null, '2', null);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (8, 'обращение с тко', null, null, '2.1', 7);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (9, 'содержание придомовых территорий', null, null, '2.2', 7);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (10, 'содержание коммунальных сетей', null, null, '2.3', 7);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (11, 'Вопросы по социальным льготам', null, null, '3', null);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (12, 'социальные льготы для многодетных семей', null, null, '3.1', 11);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (13, 'социальные льготы для инвалидов', null, null, '3.2', 11);
INSERT INTO edo.theme (id, theme_name, creation_date, archived_date, code, parent_id)
VALUES (14, 'социальные льготы для пенсионеров', null, null, '3.3', 11);
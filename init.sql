USE  test;

DROP TABLE IF EXISTS part;

CREATE TABLE part (
                      id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      name VARCHAR(100) NOT NULL,
                      essential BIT DEFAULT FALSE NOT NULL,
                      quantity INT(10)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = 'utf8'
    COLLATE='utf8_general_ci';

CREATE UNIQUE INDEX part_name_uniq ON part (name);

INSERT INTO part (name, essential, quantity)
VALUES ('Материнская плата', true, 5)
    , ('HDD диск', false, 6)
    , ('Процессор', true, 7)
    , ('Корпус', true, 11)
    , ('SSD диск', true, 18)
    , ('Оперативная память', true, 9)
    , ('Звуковая карта', false, 10)
    , ('Видеокарта', false, 1)
    , ('Подсветка корпуса', false, 1)
    , ('Дополнительное охлаждение', false, 1)
    , ('DVD-привод', false, 3)
    , ('Blue-ray привод', false, 3)
    , ('Card-reader', false, 3)
    , ('Блок питания', true, 15)
    , ('Плата видеозахвата', false, 1)
    , ('Антипылевой фильтр', false, 10)
    , ('Антивибрационный набор', false, 1)
    , ('Антивибрационное крепление вентилятора', false, 3)
    , ('Контроллер подсветки', false, 4)
    , ('Rack-модуль', false, 6)
    , ('Security контроллер', false, 5)
    , ('Raid контроллер', false, 1)
    , ('Контроллер дополнительных интерфейсов', false, 5)
     , ('Деталь 01', false, 10)
     , ('Деталь 02', false, 10)
     , ('Деталь 03', false, 10)
     , ('Деталь 04', false, 10)
     , ('Деталь 05', false, 10)
     , ('Деталь 06', false, 10)
     , ('Деталь 07', false, 10)
     , ('Деталь 08', false, 10)
     , ('Деталь 09', false, 10)
     , ('Деталь 10', false, 10)
     , ('Деталь 11', false, 10);
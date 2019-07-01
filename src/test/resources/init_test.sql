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
VALUES
      ('Деталь 01', true,  8)
    , ('Деталь 02', true,  9)
    , ('Деталь 03', true,  10)
    , ('Деталь 04', false, 10)
    , ('Деталь 05', false, 10)
    , ('Деталь 06', false, 10)
    , ('Деталь 07', false, 10)
    , ('Деталь 08', false, 10)
    , ('Деталь 09', false, 10)
    , ('Деталь 10', false, 10)
    , ('Деталь 11', false, 10)
    , ('Деталь 12', false, 10);
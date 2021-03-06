# CRUD application with Spring MVC, Maven, Hibernate, MySql, Tomcat

## Тестовое задание [JavaRush.ru](https://javarush.ru)

Требуемые технологии:
- Maven (для сборки проекта);
- Tomcat 8 или 9 (для тестирования своего приложения);
- Spring (версия не ниже 4.3.12);
- Hibernate (версия не ниже 5.3);
- MySQL (база данных (БД)). Для упрощения тестирования называйте все свою базу test, с логином и паролем root (нам не нужно будет для тестирования создавать кучу лишних и ненужных баз);
- Frontend: Spring MVC или Angular.

Приложение должно быть в виде проекта, который собирается с помощью Maven. 
Обязательно должен присутствовать скрипт для создания и наполнения тестовыми данными вашей базы данных. Предупреждение: скрипт на SQL – это скрипт, а не дамп базы данных из WorkBench, PhpMyAdmin, и прочих программ. Не ленитесь и напишите скрипт сами. В тестовые данные вставьте от 21 до 40 записей (компьютерных комплектующих).

Визуальную часть проектов UX и UI нужно придумать самому.

### Задание: PARTS (компьютерные комплектующие)

Реализовать простенькое приложение Parts-list, для отображения списка деталей для сборки компьютеров на складе. Записи хранить в базе данных. Схему таблички для хранения нужно придумать самому (достаточно одной таблицы).

Нужно показывать список уже имеющихся деталей (с пейджингом по 10 штук на странице). В списке должно быть наименование детали (String), обязательна ли она для сборки (boolean) и их количество на складе (int). На склад можно добавлять новые детали, редактировать существующие детали (любое из полей), удалять.

Должна быть сортировка по принципу:
все детали, детали, которые необходимы для сборки, опциональные детали.

Должен быть поиск по наименованию детали.

Бизнес-требование: ниже списка деталей всегда выводить поле, в котором выводится, сколько компьютеров можно собрать из деталей в наличии. Для сборки одного компьютера должны быть в наличии все детали, которые отмечены как необходимые.

## Решение

### Используемые технологии
- Spring MVC 
- Hibernate
- MySQL
- JSP, HTML, CSS, JavaScript
- Maven
- Tomcat 8.5

### Полезные ссылки
* Spring для ленивых. Основы, базовые концепции и примеры с кодом.
  * <a href="https://javarush.ru/groups/posts/spring-framework-java-1">Часть 1</a>
  * <a href="https://javarush.ru/groups/posts/477-spring-dlja-lenivihkh-osnovih-bazovihe-koncepcii-i-primerih-s-kodom-chastjh-2">Часть 2</a>
* Знакомство с Maven, Spring, MySQL, Hibernate и первое CRUD приложение
  *  <a href="https://javarush.ru/groups/posts/2253-znakomstvo-s-maven-spring-mysql-hibernate-i-pervoe-crud-prilozhenie-chastjh-1">Часть 1</a>
  *  <a href="https://javarush.ru/groups/posts/2252-znakomstvo-s-maven-spring-mysql-hibernate-i-pervoe-crud-prilozhenie-chastjh-2">Часть 2</a>
  *  <a href="https://javarush.ru/groups/posts/2251-znakomstvo-s-maven-spring-mysql-hibernate-i-pervoe-crud-prilozhenie-chastjh-3">Часть 3</a>
  *  <a href="https://javarush.ru/groups/posts/2250-znakomstvo-s-maven-spring-mysql-hibernate-i-pervoe-crud-prilozhenie-chastjh-4">Часть 4</a>
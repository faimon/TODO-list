# Проект "TODO list"
[![Build Status](https://travis-ci.org/faimon/TODO-list.svg?branch=master)](https://travis-ci.org/faimon/TODO-list)

### Web приложение для учета своих дел.
**Функциональность:**
* Авторизация и регистрация пользователя.
* Каждый пользователь имеет свой список дел.
* Пользователь может добавить или отметить задачу как решенную в списке.
* Пользователю отображаются *невыполненные* задачи.
* Возможность показа *всех* задач за все время.

**Используемые технологии:** 

**Backend**: Java 14, Java EE, Hibernate, PostgreSQL, Servlets, JSP.

**Frontend**: HTML, CSS, Javascript, Jquery.

Главная страница - авторизованный пользователь видит свои невыполненные дела:

![GitHub Logo](https://github.com/faimon/todo_list/blob/master/screenshots/main%20page.png?raw=true)


Сдвинув ползунок 'Показать все задачи', пользователь увидит все задачи:

![GitHub Logo](https://github.com/faimon/todo_list/blob/master/screenshots/main%20page%20%232.png?raw=true)


Неавторизованный пользователь, не видит чужие списки дел:

![GitHub Logo](https://github.com/faimon/todo_list/blob/master/screenshots/unauthorized%20page.png?raw=true)


Страница с регистрацией:

![GitHub Logo](https://github.com/faimon/todo_list/blob/master/screenshots/reg%20page.png?raw=true)

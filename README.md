# VirtualLibrary

###Описание

Данное веб-приложение представляет собой онлайн-библиотеку с возможностью постраничной выборки книг по автору, жанру и поиску.
Также имеется панель администратора для управления контентом (Пока переключение режимов выполняется селектором вручную)

###Примечание

В данный момент приложение находится в стадии разработки. В дальнейшем планируются такие обновления:
* Тесты
* Аутентификация пользователей (Spring Security)
* Алфавитный указатель
* Добавление книг в избранное (для зарегистрированных пользователей)
* Рейтинг
* Рассылка почтовых уведомлений при добавлении новых книг

###Используемые технологии

Бэкенд:
* Spring Data
* Spring Web MVC
* Hibernate

Фронтенд:
* Angular JS
* Angular UI
* Twitter Bootstrap

###Cборка и запуск
* Для хранения данных и контента в приложении используются БД MySQL (возможно в дальнейшем сохранение файлов
будет реализовано через отдельный сервис), поэтому для запуска необходимо скачать [dump базы данных](https://yadi.sk/d/Y4PdrAvbxX3Ja) и выгрузить его, создав перед этим базу с названием ***virt_library*** .
Для корректной работы с файлами необходимо предварительно изменить переменную ***max_allowed_packet***
в конфигурационном файле MySQL соединения (значение необходимо указать не менее ***20М***)  
* Пользователь и пароль указаны в файле ***db.properties*** по пути **src\main\resources\properties\**
* Далее проект собирается с помощью Maven, деплоится на сервер и запускается в любой IDE

Тестовое задание
=====================

Получение исходных текстов
---------------------

Для получения исходных текстов клонируйте репозиторий при помощи GIT

    git clone https://github.com/jfelix-mgn/vacancy-test.git

или скачайте ZIP архив [master.zip](https://github.com/jfelix-mgn/vacancy-test/archive/master.zip) и разорхивируйте

В качестве сборщика используется Gradle. Wrapper присутствует вместе с исходниками.
Для компиляции требуется Java 6 или выше.

Сборка проекта
---------------------

Для сборки необходимо в коммндной строке, находясь в директории с проектом запустить

    #Для операционных систем семейства Unix
    ./gradlew clean createWar

    #Для операционных систем семейства Windows
    gradlew.bat clean createWar

Запуск проекта
---------------------

После выполнения сборки в каталоге с проектом появится собранный WAR файл, который уже можно деплоить
на сервер придожений. Работоспособность проекта проверена на слежующих серверах:

- Tomcat6
- Tomcat7
- Jboss EAP 6.3

Дакже можно запустить проект на локальном сервере Tomcat 6 или 7. Для этого нужно запустить
сборщик с параметрами:

    #Для операционных систем семейства Unix
    #Для запуска Tomcat6
    ./gradlew deployTomcat6 -Ptomcat.local.dir=<Директория> [-Ptomcat.local.port=<Порт>]
    #Для запуска Tomcat7
    ./gradlew deployTomcat7 -Ptomcat.local.dir=<Директория> [-Ptomcat.local.port=<Порт>]

    #Для операционных систем семейства Windows
    #Для запуска Tomcat6
    gradlew.bat deployTomcat6 -Ptomcat.local.dir=<Директория> [-Ptomcat.local.port=<Порт>]
    #Для запуска Tomcat7
    gradlew.bat deployTomcat7 -Ptomcat.local.dir=<Директория> [-Ptomcat.local.port=<Порт>]

где -Ptomcat.local.dir - директория в которой расположен Tomcat6, а -Ptomcat.local.port - порт
Web сервера Tomcat. Порт указывать не обязательно, по умолчанию будет использоваться порт 8080

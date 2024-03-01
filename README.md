![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Debian](https://img.shields.io/badge/Debian-D70A53?style=for-the-badge&logo=debian&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

Сведения о погоде запрашиваются с сайта https://openweathermap.org
Данный сайт был выбран в качестве поставщика сведений, поскольку он бесплатный, а также не требует регистрации или создания проекта на сайте.

Поскольку задание предполагает возможность расширения поддерживаемых сервисов погоды, функционал получения погоды вынесен в отдельный класс, который инжектится через интерфейс с использованием аннотации @ConditionalOnProperty

Взаимодействие пользователя с приложением осуществляется через Swagger, который открывается по адресу: http://localhost:8080/swagger-ui/index.html
Версия приложения «подтягивается» из конфигурационного файла.

В приложении имеются два Контроллера и два Сервиса: MainService.java и ValidationService.java

ValidationService.java осуществляет проверку правильности указания пользователем  координат с использованием регулярных выражений, а также реализует логику проверки пользовательского ввода, предусмотренную техническим заданием касательно используемого языка и наличия сообщения. Выбор допустимых языков из перечня [ru, en, es] вынесен в конфигурационный файл.

Исключения перехватываются в контроллере с помощью ExceptionHandler.

Контроллер Controller.java предоставляет эндпойнт, который должен вызываться Сервисом А для передачи в адаптер неадаптированного сообщения. Это делается пользователем через Swagger. 

Контроллер DummyServiceBController.java имитирует контроллер Сервиса Б. Он содержит эндпойнт, который должен вызываться Адаптером для передачи Сервису Б уже адаптированного сообщения, обогащенного сведениями о погоде. Данный эндпойнт вызывается адаптером и служит исключительно для демонстрации обращения к удаленному ресурсу через RestTemplate. Полученное от адаптера сообщение выводится в консоль. 

В конфигурационном файле задается последовательность нод JSONа. В этой последовательности осуществляется поиск ноды с температурой. Это необходимо для того, чтобы избежать внесения изменений в код в случае изменения структуры ответа Сервиса, сообщающего погоду. Логика поиска интересующей ноды реализована в утильном классе. 

Логирование осуществляется как в файл, так и путем вывода сообщений в консоль.

Тестирование не делал, т.к. техзаданием оно не предусмотрено, к тому же неизвестно заранее, какую температуру сообщит Сервис погоды. Поэтому отсутствует возможность проверить правильность ответа. 


Spring Boot приложение для управления задачами с аутентификацией и авторизацией.

## Технологии

- Java 17
- Spring Boot 3.5.0
- PostgreSQL
- JWT для аутентификации
- Spring Data JPA
- Spring Security
- Lombok
- MapStruct
- Docker
- Flyway

## Функционал

- Регистрация и аутентификация пользователей
- CRUD операции для задач
- Ролевая модель (USER, ADMIN)
- Документация API через Swagger

## Установка

### Требования

- Docker и Docker Compose
- Maven

### Инструкция по запуску

1. Скачайте zip-архив с gitHub, распакуйте, запустите.
2. Создайте .env файл (используйте свой секретный ключ - ссылка на генерацию ключа: https://www.random.org/strings/?num=1&len=32&digits=on&upperalpha=on&loweralpha=on&unique=on&format=html&rnd=new):

   ## DB_URL=jdbc:postgresql://db:5432/planning
   ## DB_USERNAME=postgres
   ## DB_PASSWORD=111
   ## JWT_SECRET=сгенерированный ключ
   ## JWT_EXPIRATION=36000000

3. в терминале введите команду: docker-compose --env-file .env up --build 
4. Приложение будет доступно по адресу: http://localhost:8080/swagger-ui/index.html


## Аутентификация
POST /api/auth/register_admin - регистрация админа

POST /api/auth/register - регистрация

POST /api/auth/login - вход

## Пользователь
GET /api/users/{id} - получить пользователя по id

PUT /api/users/{id} - обновить данные польззователя

DELETE /api/users/{id} - удалить пользователя

## Задачи
GET /api/tasks - получить список всех задач

POST /api/tasks - создать задачу

GET /api/tasks/{id} - получить задачу по ID

PUT /api/tasks/{id} - обновить задачу

DELETE /api/tasks/{id} - удалить задачу
# Система управления поставками

# 🚀 Инструкция по запуску проекта

## 1. Клонируем репозиторий

```bash
git clone https://github.com/danilgordienko/delivery-app
```

## 2. Создаем .env в корне проекта
пример необходимых переменных можно взять в файле .env.example

## 3. Запуск через Docker

```bash
docker network create delivery_app
docker-compose up --build -d
```
## 4. Доступ к приложению

API: http://localhost:8081/api/...

Front: .\src\main\resources\templates\index.html

Swagger UI: http://localhost:8081/swagger-ui/index.html

OpenAPI JSON: http://localhost:8081/api-docs

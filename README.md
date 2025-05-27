# Система управления банковскими картами

**Brief**  
Этот проект предназначен для управлеия картами и пользователмя банка.

---

## 📂 Content

1. [Description](#-description)
2. [Installation](#-installation)
3. [Exploitation](#-exploitation)
4. [Technologies](#-technologies)
5. [Functionality](#-functionality)

---

## 📝 Description

Этот проект предоставляет API REST для работы с картами и управления пользователмя.

---

## 🚀 Installation

1. Установите репозиторий:
   ```bash
   git clone https://github.com/Artem-kn9z/Bank_REST.git

2. Перейдите в папку с проектом:

   ```bash
   cd 'Your folder with the project'

3. Установите зависимости:

   ```bash
   mvn install

4. Кроме того вам необходимо настроить среду для себя. Перед стартом, зайдите в src/main/resources/application.yml и подставте свои значения url, username, password.

   ```
       url: jdbc:postgresql://localhost:5432/bank_app_db
       username: postgres
       password: 1111

## 💻 Exploitation

1. Вы можете начать работать с приложением, запустите приложение через кнопку run, предварительно зайдя в файл src/main/com.example.bankcards/BankRESTApplication

2. Используйте приложение для отправки запросов, например, Postman.
   
    * GET: http://localhost:8182/api/admin/cards
    * GET: http://localhost:8182/api/admin/cards/1
    * POST: http://localhost:8182/api/admin/cards
    * PUT: http://localhost:8182/api/admin/cards/1
    * PUT: http://localhost:8182/api/admin/cards/1/status
    * GET: http://localhost:8182/api/admin/users

   P.S. Вы можете найти DTO схемы для POST запросов в структуре проекта.

---

## 🛠️ Technologies

* Java 17
* Spring Boot
* Spring Data JPA
* Maven
* PostgreSQL
* Liquibase
* Lombok

---

## ✨ Functionality

* Получение информации о картах и пользователях 
* CRUD карт и пользователей 

## 🚧 Process of development 

* Переводы между своими картами
* Запрос блокировки карты
* Постраничная выдача
* Ролевой доступ
* Docker Compose для dev-среды
* Unit-тесты


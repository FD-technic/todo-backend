# Todo Backend

Simple To-Do REST API built with Spring Boot.

## 🚀 Features

* Create task
* List tasks (pagination)
* Update task
* Delete task
* Mark task as completed

## 🧱 Tech stack

* Java
* Spring Boot (Web, JPA)
* MariaDB

## ▶️ Run locally

```bash
git clone https://github.com/TVUJ_USERNAME/todo-backend.git
cd todo-backend
```

Configure database in `application.properties`

```bash
./mvnw spring-boot:run
```

## 📡 API endpoints

```
POST   /tasks
GET    /tasks
PUT    /tasks/{id}
DELETE /tasks/{id}
POST   /tasks/{id}/complete
```

## 🧠 Notes

* finishDate = completed task
* null = open task

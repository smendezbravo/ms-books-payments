# 📚 ms-books-payments

Microservicio que gestiona la creación y consulta de pedidos realizados en la tienda de libros "Relatos de Papel".

---

## 🚀 ¿Qué hace este microservicio?

- Guarda pedidos con información del libro comprado y datos del comprador
- Consulta todos los pedidos registrados
- Consulta un pedido concreto por su ID
- (En desarrollo: validación de libros vía microservicio catálogo)

---

## ⚙️ Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Maven
- PostgreSQL 16
- Hibernate (JPA)
- HikariCP (pool de conexiones)
- Postman (para pruebas)
- pgAdmin (para gestión visual de la base de datos)

---

## 🛠 Requisitos previos

- Java 17 o superior
- PostgreSQL instalado y funcionando
- Maven instalado
- IDE como VS Code, IntelliJ o Spring Tool Suite
- (Opcional) pgAdmin para crear/ver la base de datos

---

## 🧪 Instalación y ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/tuusuario/ms-books-payments.git
cd ms-books-payments
```

### 2. Crea la base de datos PostgreSQL

```bash
CREATE DATABASE payments;
```

### 3. Configura la conexión en src/main/resources/application.properties

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/payments
spring.datasource.username=tuusername
spring.datasource.password=tupassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true

```

### 4. Compila y arranca el microservicio

```bash
mvn clean install
mvn spring-boot:run
```
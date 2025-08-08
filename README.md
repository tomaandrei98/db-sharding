# DB Sharding with Spring Boot and PostgreSQL

## Overview

This project demonstrates **database sharding** in a Spring Boot application using **two PostgreSQL shards**. It implements data partitioning at the application layer by routing user data to one of two PostgreSQL instances based on a shard resolver that hashes the username. Each shard has its own datasource, JPA repository, and transaction manager.

The project includes:

* Two PostgreSQL shards running as Docker containers
* A Spring Boot backend with separate JPA configurations per shard
* A simple sharding strategy using hash-based routing on usernames
* PgAdmin container for easy database management and inspection

---

## Technologies Used

* Java 17+
* Spring Boot 3.x
* Spring Data JPA (Hibernate)
* PostgreSQL 16 (two separate instances as shards)
* Docker & Docker Compose
* PgAdmin 4

---

## Architecture

* **Shard1** and **Shard2**: Independent PostgreSQL containers each with own database.
* **Spring Boot app**: Configured with two datasources, entity managers, and transaction managers.
* **ShardResolver**: Routes the data to the correct shard based on the username.
* **UserShardingService**: Saves user data to the proper shard repository.

---

## Getting Started

### Prerequisites

* Docker and Docker Compose installed
* Java 17+ and Maven/Gradle to build the Spring Boot application

### Run PostgreSQL Shards and pgAdmin

From the project root, run:

```bash
docker-compose up -d
```

* Shard1 Postgres: localhost:5433
* Shard2 Postgres: localhost:5434
* PgAdmin: [http://localhost:5050](http://localhost:5050) (login with `admin@admin.com` / `admin`)

### Configure Spring Boot Application

The application uses the following datasource URLs (already set in `application.yml` or `application.properties`):

```yaml
spring:
  datasource:
    shard1:
      url: jdbc:postgresql://localhost:5433/shard1db
      username: user
      password: pass
    shard2:
      url: jdbc:postgresql://localhost:5434/shard2db
      username: user
      password: pass
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        generate_statistics: true
```

### Build and Run the Application

Use Maven to build and run:

```bash
./mvnw spring-boot:run
```

The app will start and insert sample users (`alice`, `bob`) routed to different shards.

---

## Code Structure

* `com.example.sharding.config` — Datasource and JPA config for shards
* `com.example.sharding.entity` — JPA entities
* `com.example.sharding.repository.shard1` — Repositories for shard1
* `com.example.sharding.repository.shard2` — Repositories for shard2
* `com.example.sharding.service` — Business logic, user service routing to shards
* `com.example.sharding.utils` — ShardResolver for deciding shard by username

---

## Logging and Debugging

Hibernate SQL logs and transaction traces are enabled for easy debugging. You can see SQL statements executed on each shard in the console.

---

## Future Improvements

* Add more shards dynamically
* Use distributed transactions or saga pattern for cross-shard consistency
* Add REST API endpoints for CRUD operations
* Improve shard routing with different strategies (range-based, lookup tables)
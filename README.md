topup workspace
===========================

# Stack Stack

- Java 21 with virtual thread enabled
- Apache Maven 3.9.9
- Spring Boot 3.4.0
- Vaadin 24.5
- jOOQ 3.19.15
- PostgreSQL 17
- Redis 7
- Nats Server 2.11

# Get Started

- Clone the repository
- Run `docker compose up -d` to start the database and message broker
- Run `just dbunit-operation` to create the database schema and load the initial data
- Run `mvn spring-boot:run` to start the application

# References

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Karibu-DSL: https://github.com/mvysny/karibu-dsl
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- [jOOQ](https://www.jooq.org/): an internal DSL and source code generator, modelling the SQL language as a type safe
  Java API to help you write better SQL
- [vaadin-jooq](https://github.com/martinellich/vaadin-jooq): is a small library to integrate Vaadin and jOOQ
- jOOQ Spring Integration: https://github.com/martinellich/jooq-spring

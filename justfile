dev:
    ./mvnw -DskipTests spring-boot:run

build:
    ./mvnw -DskipTests clean package

release:
    ./mvnw -Pproduction -DskipTests clean package

postgres:
    psql -d "postgres://topup:123456@127.0.0.1:15432/topup"

flyway-migrate:
    ./mvnw -Pgenerator org.flywaydb:flyway-maven-plugin:10.20.0:clean
    ./mvnw -Pgenerator org.flywaydb:flyway-maven-plugin:10.20.0:migrate

dbunit-operation: flyway-migrate
    ./mvnw -Pgenerator org.dbunit:dbunit-maven-plugin:1.2.0:operation

jooq-generate:
    ./mvnw -Pgenerator org.testcontainers:testcontainers-jooq-codegen-maven-plugin:0.0.4:generate

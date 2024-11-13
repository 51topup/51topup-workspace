dev:
    ./mvnw -DskipTests spring-boot:run

build:
    ./mvnw -DskipTests clean package

release:
    ./mvnw -Pproduction -DskipTests clean package

postgres:
    psql -d "postgres://topup:123456@127.0.0.1:15432/topup"

jooq-generate:
    ./mvnw org.testcontainers:testcontainers-jooq-codegen-maven-plugin:0.0.4:generate

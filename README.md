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

# [C4 Model](https://c4model.com/)

- Context: 整个系统，这里是指机器猫整个系统，包括多个应用、外部系统和底层基础设施
- Container: 某一独立应用、外部系统或基础设施，目前主要包括：三个内部系统 `卖家工作平台`、`外部充值对接应用`、`平台对接应用`。
- Component：支撑应用功能的组件，目前主要是Vaadin的`View`、`Component`、`Service`、`DAO`等。
- Code：具体功能对应的代码

# 淘宝开放平台
 
* 闲鱼开放平台: https://open.goofish.com/doc/
* 应用管理：https://work.open.taobao.com/open-console-ec/34912212/app_app
* 基础技术：https://open.taobao.com/doc.htm?docId=118395&docType=1
* 聚石塔：https://open.taobao.com/doc.htm?docId=101130&docType=1
* 常用工具: https://open.taobao.com/docV3.htm?docId=1&docType=15
* 闲鱼session key测试获取： https://open.api.goofish.com/authorize?response_type=token&client_id=34912212&sp=xianyu

# References

- Vaadin Docs: https://vaadin.com/docs
- Vaadin Reference Card: https://vaadin.com/vaadin-reference-card
- Vaadin add-ons: https://vaadin.com/directory/
- Vaadin Icons: https://vaadin.com/docs/latest/components/icons/default-icons
- Vaadin Cookbook: https://cookbook.vaadin.com/
- Karibu-DSL: https://github.com/mvysny/karibu-dsl
- Follow the tutorials at [vaadin.com/tutorials](https://vaadin.com/tutorials).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/components](https://vaadin.com/components).
- [jOOQ](https://www.jooq.org/): an internal DSL and source code generator, modelling the SQL language as a type safe
  Java API to help you write better SQL
- [vaadin-jooq](https://github.com/martinellich/vaadin-jooq): is a small library to integrate Vaadin and jOOQ
- jOOQ and Kotlin: https://www.baeldung.com/kotlin/jooq
- jOOQ Spring Integration: https://github.com/martinellich/jooq-spring
- jOOQ Ad-hoc Converter: https://www.jooq.org/doc/latest/manual/sql-execution/fetching/ad-hoc-converter/
- 10 Nice Examples of Writing SQL in Kotlin With
  jOOQ: https://blog.jooq.org/10-nice-examples-of-writing-sql-in-kotlin-with-jooq/

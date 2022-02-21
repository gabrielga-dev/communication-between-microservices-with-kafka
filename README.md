# Communication between microservices with Kafka and Apache Camel


Topics
=================
<!--ts-->
* [Project briefing](#project-briefing)
* [Technical information](#technical-information)
    * [Technologies](#technologies)
    * [Requirements](#Requirements)
    * [File structure](#file-structure)
* [How to execute it](#how-to-execute-it)
* [How does it work](#funcionalidades-e-seus-fluxos)
<!--te-->

Project birefing
================
This project aims to be a study resource for microservices integration with Kafka and Apache Camel. These
technologies are a good tools to make a well working product.


Technical information
================

## Technologies
- [Java 11](https://docs.oracle.com/en/java/javase/11/): language used for build all business logic
- [Spring boot](https://spring.io/projects/spring-boot): Java framework for building the microservices
- [kafka](https://kafka.apache.org/): Menssagery plataform used for transfer messages between microsservices
- [Apache Camel](https://camel.apache.org/docs/):  Integration framework used for routing incomming messages
- [Docker](https://docs.docker.com/):  service used for building containers with databases
- [MySQL](https://dev.mysql.com/doc/): Selectec database for saving the project's informations

## Requirements
- [Java 11](https://docs.oracle.com/en/java/javase/11/)
- [Docker](https://docs.docker.com/)

## File structure

- ***ecommerce-email/*** -> email microsservice's folder
- ***ecommerce-log/*** -> log microsservice's folder
- ***ecommerce-order/*** -> order microsservice's folder
- ***infra/*** -> infrastructure folder
  - ***kafka/*** -> used kafka files
  - ***mysql-docker/*** -> required files for building the MYSql database with Docker

How to execute it
================
First you need to set up the database container, to achieve it you need to go on `./infra/mysql-docker` and execute the
following command:

> docker-compose up -d
> 
> **-d** optional parameter, it says that you don't want to attach the container logs to you terminal

Once you have the database container running you will need to run the zookeeper and kafka servers. Go to the following
directory `./infra/kafka/kafka_2.12-3.1.0` and run these commands bellow 
(***the order that you run them is important!!!***):

>bin/zookeeper-server-start.sh config/zookeeper.properties
> 
Now, open a new terminal on the same directory and run:
> 
> bin/kafka-server-start.sh config/server.properties

How does it work
================

The project has 2 endpoints, one for registering new orders and another for filtering logs.

## Registering new orders

>URL: http://localhost:8082/api/order
> 
> Method: POST
> 
> Body:
> 
> ```javascript
> { 
>   "userId": "a_string", 
>   "userEmailAddress": "a_string", 
>   "amount": "a_bigdecimal" 
> }
> ```
 
When this endpoint is correctly consumed a new order is saved on our database and 2 messages are dispatched, an email
message and an order message. Once it happens, the log microservice consume the log message and register a new log the
DB and the email microservice consume the email message and send, effectively, an email to the user's email address.

## Filtering logs

>URL: http://localhost:8080/api/log?topic=STRING&type=STRING&initialDate=STRING&finalDate=STRING&PAGEABLE_DATA
>
> Method: GET

When this endpoint is correctly consumed a new log is saved on our database all saved logs are filtered and returnes to
the endpoint's consumer.
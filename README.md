# Cinema-project

## Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer)
* [Author](#author)

### <a name="purpose"></a>Project purpose

This project is a simple version of online cinema.

<hr>

It has the following functions:
- Login and registration forms.
- Shopping cart and order services
- Two roles: User and Admin

Functions for users with an ADMIN role only:
- add new movies
- add movie sessions
- add cinema halls
  
Functions for users with a USER role only: 
- add tickets to user's shopping cart
- complete orders

<hr>

### <a name="structure"></a>Project structure

- Java 11
- Maven 4.0.0
- maven-checkstyle-plugin 3.1.1
- spring-context, spring-orm, spring-webmvc 5.2.6.RELEASE
- spring-security-core, spring-security-config, spring-security-web 5.3.3.RELEASE
- hibernate-core, hibernate-java8 5.4.15.Final
- hibernate-validator 6.1.5.Final
- javax.servlet-api 4.0.1
- mysql-connector-java 8.0.20
- log4j 1.2.17

### <a name='developer'></a>For developer

To run this project you need to install:

- <a href="https://www.oracle.com/java/technologies/javase-jdk11-downloads.html">Java 11</a> or above
- <a href="https://tomcat.apache.org/download-90.cgi">Tomcat</a>
- <a href="https://www.mysql.com/downloads/">MySQL 8</a>

<hr>

Add this project to your IDE as Maven project.

Add Java SDK in project structure.

Configure Tomcat:
- Add artifact
- Add Java SDK

Change a path to your Log file in **src/main/resources/log4j.properties** on line 9.

<hr>

To work with MySQL you need to:
- Change username and password to match with MySQL in **src/main/resources/db.properties** 
on 4-5 lines.

<hr>

By default, two users will be generated:
- with an ADMIN role (login = "admin@mail.com", password = "admin")
- with a USER role (login = "user@mail.com", password = "1234")

<hr>

### <a name='author'></a>Author
[Serhii Kinashchuk](https://github.com/serg-ksv)


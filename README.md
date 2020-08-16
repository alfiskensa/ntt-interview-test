Java Test Application
=====================

Java Test Application with Spring Boot for update data from file (.csv) to database with job scheduler and REST API for modifying and filtering data

## Getting Start

You can open this porject with Eclipse IDE

## Prerequisites

What things you need to install the software and install them :

* Java 8
* Maven

## Installing

just clone project

```sh
$ git clone https://github.com/alfiskensa/test.git
$ cd test
$ mvn clean package or mvn spring-boot:run
```

## Built With
* Spring Boot
* [Quartz](quartz-scheduler.org) - For Update Data Scheduler
* [QueryDsl](http://www.querydsl.com/) - For dynamic queries
* Apache Commons Libraries - for utilities common function (CSV Helper,File,IO etc)

## Authors
[Alfi Ramdhani](https://github.com/alfiskensa)

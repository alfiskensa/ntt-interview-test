Java Test Application
=====================

Java Test Application with Spring Boot for update data from file (.csv) to database with job scheduler and REST API for modifying and filtering data

## Getting Start

You can open this project with Eclipse IDE

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

before running and testing app make sure csv files is exist in ./data/uploads for reading data and write it to database



## API Documentation

Note: *please run application first to enable [links](http://localhost:8080/swagger-ui.html) for specification details*
* [Show All Grade of Students](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/test-controller/findGrades) : `GET ​/test​/grade?regNumber={nomor_induk}&studentName={"nama_siswa"}&courseName={mata_pelajaran}&score={nilai}` PS: *the query parameters are optional*
* [Update Grade](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/test-controller/updateGrade) : `PUT ​/test​/grade` for specification request body just need studentId, courseId and score
* [Delete Grade](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/test-controller/deleteGrade) : `DELETE ​/test​/grade​/{id}` id of grade in table
* [Scheduler Execute Manually](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/scheduler-controller/executeManually) : `GET ​/scheduler​/execute` this will be manually execute the job for getting data in csv file and store it to database
* [Reschedule Cron](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/scheduler-controller/rescheduleJob) : `POST ​/scheduler​/reschedule?cron={cron_expression}` you can generate cron expression [here](https://www.freeformatter.com/cron-expression-generator-quartz.html)



## Future Development
* all files will in directory ./data/uploads will be read by application during scheduler is running. It can happens if there are many files in directory, application will busy to read files while the file is already executed. To be concern this issue. We can make Regular Expression validation to read data by speficiation date. So the file name will be siswa_20200816_184056.csv which `20200816` is the current date and `184056` is specification time. So it can reduce application usage to read files which is the file is validated with specification datetime. The code can be found in [here](/src/main/java/com/example/test/job/UpdateDataJob.java)


## Built With
* Spring Boot
* [Quartz](quartz-scheduler.org) - for Update Data Scheduler
* [QueryDsl](http://www.querydsl.com/) - for dynamic queries
* Apache Commons Libraries - for utilities common function (CSV Helper,File,IO etc)
* [OpenAPI](https://github.com/springdoc/springdoc-openapi) - for API Documentation


## Authors
[Alfi Ramdhani](https://github.com/alfiskensa)

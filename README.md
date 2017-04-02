[![Build Status](https://travis-ci.org/cod3hulk/recommendationservice.svg?branch=master)](https://travis-ci.org/cod3hulk/recommendationservice)
[![Code Coverage](https://codecov.io/gh/cod3hulk/recommendationservice/branch/master/graph/badge.svg)](https://codecov.io/gh/cod3hulk/recommendationservice)
[![Code Quality](https://codebeat.co/badges/91565840-a808-44f5-87c8-93e97fb46216)](https://codebeat.co/projects/github-com-cod3hulk-recommendationservice-master)
# Product Recommendation Service
Simple product recommendation service for storing and retrieving product recommendations of customers

## API Guide
An API guide for the service is provided by [Spring REST Docs](https://projects.spring.io/spring-restdocs/). 

### Generate API Guide
The API guide can be generated by `mvn clean package`. The generated guide can be found in 
`target/generated-docs/api-guide.html` as HTML file.

### API Guide served as static content
The API guide is also served as static content as part of the service and can be accessed via  
`http://{HOST}/docs/api-guide.html`

## Docker Integration
The service can be packaged as Docker image using the [Spotify docker-maven-plugin](https://github
.com/spotify/docker-maven-plugin) 

### Create Docker image
To create a Docker image of the service run `mvn clean package docker:build`. A running docker daemon is required for
this task.

### Starting a Docker container
When you have created a Docker image of the service you can create a container by executing
`docker run --name recommendationservice -p 8080:8080 -d com.acme/recommendationservice`. The service will be reachable
at `localhost:8080`.

## Database Configuration
The service currently uses an [H2](http://www.h2database.com/html/main.html) inmemory database. Any other third party 
database can be easily used by setting the `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` 
properties in the `application.properties`. If you want to use another database you also need to
provide the database driver as maven dependency. 

## Notes
The service currently only uses a single table for storing the relevant information. If the recommendation data gets 
more complex (e.g. additional information about the game) it might be a better solution to split the data into different
models and tables.

The CSV upload is currently run synchronous. For large CSV files it might be a better solution to run the processing
asynchronous or using some kind of batch framework (e.g. [Spring Batch](http://projects.spring.io/spring-batch/))

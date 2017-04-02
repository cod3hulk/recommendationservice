[![Build Status](https://travis-ci.org/cod3hulk/recommendationservice.svg?branch=master)](https://travis-ci.org/cod3hulk/recommendationservice)
[![Code Coverage](https://codecov.io/gh/cod3hulk/recommendationservice/branch/master/graph/badge.svg)](https://codecov.io/gh/cod3hulk/recommendationservice)
[![Code Quality](https://codebeat.co/badges/91565840-a808-44f5-87c8-93e97fb46216)](https://codebeat.co/projects/github-com-cod3hulk-recommendationservice-master)
# Product Recommendation Service
Simple product recommendation service for storing and retrieving product recommendations of customers

## Docker Integration
Create docker image: mvn clean package docker:build
Run docker container: docker run --name recommendationservice -p 8080:8080 -d com.acme/recommendationservice

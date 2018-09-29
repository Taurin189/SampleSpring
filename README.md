# Springboot web Sample application
The purpose of this repo is to practice spring boot web application. 

## What is this application?
This application is very simple todo list.

|URI|Page Name|
|:-----|:--------:|
|/|todo list page|
|/login|login page|

## How to run?
### Start MySQL docker container
```bash
$ cd ${APPLICATION_ROOT}/docker/mysql
$ docker-compose up -d
```

### Start Application
```bash
$ cd ${APPLICATION_ROOT}/
$ gradle bootRun
```
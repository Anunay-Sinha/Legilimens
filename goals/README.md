# Goal Service
This services is responsible for managing Goals. It supports CRUD on goals along with
high order API's. It also manages all interactions related to Goals setting, monitoring etc.

## Design

##Implementation
It is implemented as a spring boot project 

## Dependencies

* Mongo
* Common Utils (for now exposed from user service)

## Build

Requires gradle for build  

``` gradle clean build``` 

Post build we can use gradle to start  

```gradle bootrun```

Or  
Use **docker**.

For building an image we need to have a jar. Build command above lets you compile the 
project. If successful,it will create build folder, where you will find libs and under
libs we will have our jar.

**DockerFile** provided here can be used to build the docker locally  

``` docker build --tag leg-goal:0.0.1-snapshot .``` 

We have profile called dkr for docker specific configs.

For running the docker image use the following command

``` docker run -p 9091:9091 -e "SPRING_PROFILES_ACTIVE=dkr" --name lgoal leg-goal:0.0.1-snapshot```

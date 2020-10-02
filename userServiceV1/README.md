# User Service

This service is responsible for managing users. 
For now it also stores common response pojos which will be migrated later

# Design

# Implementation
Its implemented as a spring boot project.  
This is also a candidate to me moved out to GO

# Dependencies

* Mongo


# Build

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

``` docker build --tag leg-user:0.0.1-snapshot .``` 

We have profile called dkr for docker specific configs.

For running the docker image use the following command

``` docker run -p 9090:9090 -e "SPRING_PROFILES_ACTIVE=dkr" --name luser leg-user:0.0.1-snapshot```
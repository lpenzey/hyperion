[![Build Status](https://travis-ci.org/lpenzey/hyperion.svg?branch=master)](https://travis-ci.org/lpenzey/hyperion) [![Maintainability](https://api.codeclimate.com/v1/badges/60d82bfebc6fec4f85d7/maintainability)](https://codeclimate.com/github/lpenzey/hyperion/maintainability) [![Test Coverage](https://api.codeclimate.com/v1/badges/60d82bfebc6fec4f85d7/test_coverage)](https://codeclimate.com/github/lpenzey/hyperion/test_coverage)



### Hyperion HTTP Server
A web server that implements HTTP written in Java. 

### Requirements
- Software: [Java 11](https://adoptopenjdk.net/)
- Build tool: [Gradle 5.4.1](https://gradle.org/install/)


### Getting Started
Clone this repo to your local machine: https://github.com/lpenzey/hyperion.git

### Usage
1 - In the terminal, navigate to the cloned project's root folder and run the following command to create a package:
```
$ ./gradlew jar
```
2 - Then run the following to build the project:
```
$ ./gradlew build
```
3 - Once built run the following to start the server on a given port (port 5000 in the example below):
```
$ java -jar build/libs/hyperion.jar 5000
```
4 - Send a request to the server on the same port:
```
localhost:5000/simple_get
```

***Note that in the server's current phase it only returns a 200 OK status and no body when given "/simple_get" as the path. 
All other requests will return 404 Not Found.***

### Running the tests
In the terminal window, navigate to the project's root folder and run the following command:
```
$ gradle test
```


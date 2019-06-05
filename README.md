

### Hyperion HTTP Server
A web server that implements HTTP written in Java. 

### Requirements
- Software: [Java 11](https://adoptopenjdk.net/)
- Build tool: [Gradle 5.4.1](https://gradle.org/install/)


### Getting Started
Clone this repo to your local machine: https://github.com/lpenzey/hyperion.git

### Usage
1. Start the server: In a terminal window, cd into the project directory's src folder:
```
$ cd hyperion/src
```
Then run the following command to compile and run the server, making sure to pass in a port number 
(running the below command will start the server on port 5000)
```
$ javac main/java/server/Server.java && java main/java/server/Server 5000
```
2. Once running on the specified port, enter the following in a web browser 
```
127.0.0.1:5000
```
This will return a 404 Not Found code. To receive a 200 OK response, enter the following:
```
127.0.0.1:5000/simple_get
```

### Running the tests
In the terminal window, navigate to the project's root folder and run the following command:
```
$ gradle test
```


package main.java.server;

import main.java.server.request.Request;

import java.util.HashMap;

public class Router {

    public String route(Request request) {
        HashMap<String, String> requestHeaders = request.getHeaders();
        String path = requestHeaders.get("path");
        if (path.equals("/simple_get")) {
            return "HTTP/1.1 200 OK\r\n";
        } else {
            return "HTTP/1.1 404 Not Found\r\n";
        }
    }

}

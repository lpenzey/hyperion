package main.java.server;

import main.java.server.request.Request;

import java.util.HashMap;

import static main.java.server.StatusCodes.*;

public class Router {

    public String route(Request request) {
        HashMap<String, String> requestHeaders = request.getHeaders();
        String path = requestHeaders.get("path");
        if (path.equals("/simple_get")) {
            return OK;
        } else {
            return NOT_FOUND;
        }
    }

}

package main.java.server;

import java.util.HashMap;
import main.java.server.request.Request;

import static main.java.server.StatusCodes.NOT_FOUND;
import static main.java.server.StatusCodes.OK;

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

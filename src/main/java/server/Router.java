package main.java.server;

import main.java.server.HTTPMessageComponents.HTTPMethods;
import main.java.server.request.Request;
import main.java.server.response.Handler;
import main.java.server.response.Response;
import main.java.server.response.ResponseTypes;

import java.util.HashMap;
import java.util.TreeMap;

import static main.java.server.HTTPMessageComponents.HTTPMethods.*;

public class Router implements Handler {
    public TreeMap<String, HashMap<String, Handler>> routes;

    public Router() { this.routes = new TreeMap<>(); }

    public TreeMap<String, HashMap<String, Handler>> routes() { return routes; }

    public void get(String path, Handler handler) { addToRoutes(GET, path, handler); }

    public void head(String path, Handler handler) { addToRoutes(HEAD, path, handler); }

    public void options(String path, Handler handler) {
        addToRoutes(OPTIONS, path, handler);
    }

    public void put(String path, Handler handler) {
        addToRoutes(PUT, path, handler);
    }

    public void post(String path, Handler handler) {
        addToRoutes(POST, path, handler);
    }

    private void addToRoutes(HTTPMethods method, String path, Handler handler) {
        routes.putIfAbsent(path, new HashMap<>());
        routes.get(path).put(method.name(), handler);
    }

    public Response generateResponse(Request request) {

        if (!pathExists(request)) {

            return ResponseTypes.notFound(request);
        }

        if (!methodAllowed(request)) {
            HashMap<String, Handler> pathMethods = routes.get(request.getRequestPath());

            return ResponseTypes.notAllowed(request, pathMethods);
        }

        return routes.get(request.getRequestPath()).get(request.getRequestMethod().name()).generateResponse(request);
    }

    private boolean pathExists(Request request) {
        return routes()
                .keySet()
                .contains(request.getRequestPath());
    }

    private boolean methodAllowed(Request request) {
        return routes()
                .get(request.getRequestPath())
                .containsKey(request.getRequestMethod().name());
    }
}

package main.java.server;

import main.java.server.response.Handler;

import java.util.HashMap;
import java.util.TreeMap;

import static main.java.server.HTTPMessageComponents.HTTPMethods.*;

public class Router {
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

    private void addToRoutes(String method, String path, Handler handler) {
        routes.putIfAbsent(path, new HashMap<>());
        routes.get(path).put(method, handler);
    }
}

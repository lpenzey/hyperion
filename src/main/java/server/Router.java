package main.java.server;

import main.java.application.Handler;

import java.util.HashMap;
import java.util.TreeMap;

import static main.java.server.HTTPMessageComponents.HTTPMethods.*;

public class Router {
    public TreeMap<String, HashMap<String, Handler>> routes;

    public Router() { this.routes = new TreeMap<>(); }

    public TreeMap<String, HashMap<String, Handler>> routes() { return routes; }

    public void get(String route, Handler handler) { addToRoutes(GET, route, handler); }

    public void head(String route, Handler handler) { addToRoutes(HEAD, route, handler); }

    public void options(String route, Handler handler) {
        addToRoutes(OPTIONS, route, handler);
    }

    public void put(String route, Handler handler) {
        addToRoutes(PUT, route, handler);
    }

    public void post(String route, Handler handler) {
        addToRoutes(POST, route, handler);
    }

    private void addToRoutes(String method, String route, Handler handler) {
        routes.putIfAbsent(route, new HashMap<>());
        routes.get(route).put(method, handler);
    }
}

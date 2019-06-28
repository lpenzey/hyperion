package main.java.application;

import main.java.server.Router;
import main.java.server.response.Handler;

import java.util.HashMap;
import java.util.TreeMap;

public class App {

    private Routes routes;

    public App() { }

    public App(Routes routes) {
        this.routes = routes;
    }

    public TreeMap<String, HashMap<String, Handler>> routes() {
        return routes.ROUTES;
    }

    public Router router() {
        return routes.ROUTER;
    }

}
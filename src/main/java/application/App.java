package main.java.application;

import java.util.HashMap;
import java.util.TreeMap;

public class App {

    private TreeMap<String, HashMap<String, Handler>> routes;

    public App() { }

    public App(TreeMap<String, HashMap<String, Handler>> routes) {
        this.routes = routes;
    }

    public TreeMap<String, HashMap<String, Handler>> routes() {
        return routes;
    }
}

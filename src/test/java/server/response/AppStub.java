package test.java.server.response;

import main.java.application.App;
import main.java.server.response.Handler;

import java.util.HashMap;
import java.util.TreeMap;

public class AppStub extends App {

    private TreeMap<String, HashMap<String, Handler>> routes;

    AppStub(TreeMap<String, HashMap<String, Handler>> routes) {
        this.routes = routes;
    }

    public TreeMap<String, HashMap<String, Handler>> routes() {
        return routes;
    }
}

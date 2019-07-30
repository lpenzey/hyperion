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
    static public final String WITH_DELIMITER = "(?=%1$s)";

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
        String path = cleanPath(request.getRequestPath());

        if (!pathExists(path)) {
            return ResponseTypes.notFound(request);
        }

        if (!methodAllowed(request, path)) {
            HashMap<String, Handler> pathMethods = routes.get(path);

            return ResponseTypes.notAllowed(request, pathMethods);
        }

        return routes.get(path).get(request.getRequestMethod().name()).generateResponse(request);
    }

    private boolean pathExists(String path) {
        return routes()
                .keySet()
                .contains(path);
    }

    private boolean methodAllowed(Request request, String path) {
        return routes()
                .get(path)
                .containsKey(request.getRequestMethod().name());
    }

    private String cleanPath(String path){
        String[] splitPath = path.split(String.format(WITH_DELIMITER, "/"));
        String endPath = splitPath[splitPath.length - 1];

        if(endPath.contains("?")){
            endPath = endPath.split("\\?")[0];
        }
        return endPath;
    }
}

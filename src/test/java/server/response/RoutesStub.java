package test.java.server.response;

import main.java.application.Handler;
import main.java.server.request.Request;
import main.java.server.response.Response;
import main.java.server.Router;

import java.util.HashMap;
import java.util.TreeMap;

public class RoutesStub {
    static TreeMap<String, HashMap<String, Handler>> ROUTES;

    RoutesStub() {
        Router router = new Router();
        router.get("/simple_get", HandlersStub.SimpleGet);

        ROUTES = router.routes();
    }

    public static class HandlersStub {
        public static final Handler SimpleGet = (Request request) -> new Response("HTTP/1.1 200 OK\r\n");
    }
}




package main.java.application;

import main.java.server.Router;
import main.java.server.response.Handler;

import java.util.HashMap;
import java.util.TreeMap;

public class Routes {
    public static TreeMap<String, HashMap<String, Handler>> ROUTES;
    public static Router ROUTER;

    static {
        Router router = new Router();
        router.get("/simple_get", Handlers.SimpleGet);
        router.get("/", Handlers.SimpleGet);
        router.head("/simple_get", Handlers.SimpleGet);
        router.options("/simple_get", Handlers.SimpleOptions);

        router.head("/get_with_body", Handlers.GetWithBody);
        router.options("/get_with_body", Handlers.GetWithBody);

        router.options("/method_options", Handlers.SimpleOptions);
        router.get("/method_options", Handlers.SimpleGet);
        router.head("/method_options", Handlers.SimpleGet);

        router.get("/method_options2", Handlers.SimpleGet);
        router.options("/method_options2", Handlers.SimpleOptions);
        router.head("/method_options2", Handlers.SimpleGet);
        router.put("/method_options2", Handlers.EchoBody);
        router.post("/method_options2", Handlers.EchoBody);

        router.post("/echo_body", Handlers.EchoBody);

        router.get("/redirect", Handlers.Redirect);

        router.get("/http://www.ctabustracker.com/bustime/api/v2/getroutes", Handlers.Proxy);
        router.get("/http://www.ctabustracker.com/bustime/api/v2/getpredictions", Handlers.Proxy);
        router.get("/http://www.ctabustracker.com/bustime/api/v2/getpatterns", Handlers.Proxy);
        router.get("/http://www.ctabustracker.com/bustime/api/v2/getstops", Handlers.Proxy);
        router.get("/http://www.ctabustracker.com/bustime/api/v2/getdirections", Handlers.Proxy);

        ROUTES = router.routes();
        ROUTER = router;
    }
}

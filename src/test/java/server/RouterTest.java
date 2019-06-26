package test.java.server;

import main.java.server.response.Handler;
import main.java.server.request.Request;
import main.java.server.request.StatusLine;
import test.java.server.response.RoutesStub.HandlersStub;
import main.java.server.Router;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static main.java.server.HTTPMessageComponents.HTTPMethods.GET;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;

public class RouterTest {
    private Router router;

    @Before
    public void setUp() {
        router = new Router();
    }

    @Test
    public void addGetRouteToRouter()  {
        HandlersStub handlers = new HandlersStub();
        Handler handler = handlers.SimpleGet;
        Request request = new Request(new StatusLine(GET, "/test_path", VERSION));

        router.get("/test_path", handler);

        assertTrue(router.routes().containsKey("/test_path"));
    }

}


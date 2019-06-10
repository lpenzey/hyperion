package test.java.server;

import main.java.server.Router;
import main.java.server.request.Request;
import main.java.server.request.RequestParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RouterTest {

    private Router router;
    private RequestParser requestParser;

    private static final String KNOWN_REQUEST = "GET /simple_get HTTP/1.1\r\n";
    private static final String UNKNOWN_REQUEST = "GET /huh? HTTP/1.1\r\n";
    private static final String OK = "HTTP/1.1 200 OK\r\n";
    private static final String NOT_FOUND = "HTTP/1.1 404 Not Found\r\n";

    @Before
    public void setUp() {
        router = new Router();
        requestParser = new RequestParser();
    }

    @Test
    public void returnsNotFoundIfPathIsUnknown() {
        Request request = requestParser.create(UNKNOWN_REQUEST);
        String response = router.route(request);

        assertEquals(NOT_FOUND, response);
    }

    @Test
    public void returnsOKIfPathIsKnown() {
        Request request = requestParser.create(KNOWN_REQUEST);
        String response = router.route(request);

        assertEquals(OK, response);
    }

    @Test
    public void doesNotReturnOkIfPathIsUnknown() {
        Request request = requestParser.create(UNKNOWN_REQUEST);
        String response = router.route(request);

        assertNotEquals(OK, response);
    }
}


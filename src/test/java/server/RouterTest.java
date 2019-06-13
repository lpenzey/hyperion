package test.java.server;

import main.java.server.Response;
import main.java.server.request.Request;
import main.java.server.request.RequestParseException;
import main.java.server.request.RequestParser;
import main.java.server.router.ServerRouter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RouterTest {

    private ServerRouter router;
    private RequestParser requestParser;

    private static final String KNOWN_GET_REQUEST = "GET /simple_get HTTP/1.1\r\n";
    private static final String UNKNOWN_REQUEST = "OPTIONS /huh? HTTP/1.1\r\n";
    private static final String HEAD_REQUEST = "HEAD /get_with_body HTTP/1.1\r\n";
    private static final String OK_RESPONSE = "HTTP/1.1 200 OK\r\n";
    private static final String NOT_FOUND_RESPONSE = "HTTP/1.1 404 Not Found\r\n";

    @Before
    public void setUp() {
        router = new ServerRouter();
        requestParser = new RequestParser();
    }

    @Test
    public void returnsNotFoundIfPathIsUnknown() throws RequestParseException {
        Request request = requestParser.create(UNKNOWN_REQUEST);
        Response response = router.route(request);

        assertEquals(NOT_FOUND_RESPONSE, response.getStatusLine());
    }

    @Test
    public void returnsOKIfPathIsKnown() throws RequestParseException {
        Request request = requestParser.create(KNOWN_GET_REQUEST);
        Response response = router.route(request);

        assertEquals(OK_RESPONSE, response.getStatusLine());
    }

    @Test
    public void doesNotReturnOkIfPathIsUnknown() throws RequestParseException {
        Request request = requestParser.create(UNKNOWN_REQUEST);
        Response response = router.route(request);

        assertNotEquals(OK_RESPONSE, response.getStatusLine());
    }

    @Test
    public void returnsHeaderWithNoBodyForHEADRequest() throws RequestParseException {
        Request request = requestParser.create(HEAD_REQUEST);
        Response response = router.route(request);

        assertEquals(OK_RESPONSE, response.getStatusLine());
    }
}


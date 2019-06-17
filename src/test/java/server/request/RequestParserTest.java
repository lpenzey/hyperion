package test.java.server.request;

import main.java.server.request.Request;
import main.java.server.request.RequestParseException;
import main.java.server.request.RequestParser;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertTrue;
import static main.java.server.HTTPMessageComponents.HTTPMethods.GET;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.VERSION;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private RequestParser requestParser;

    private static final String SIMPLE_GET = "GET /simple_get HTTP/1.1\r\n";
    private static final String MALFORMED_REQUEST = "GET /simple_get\r\n";
    private static final String SIMPLE_GET_PATH = "/simple_get";
    private static final String REQUEST_WITH_HEADERS = "GET /a_path HTTP/1.1\r\n" +
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3)\n" +
            "Host: isitchristmas.com\n" +
            "Connection: Keep-Alive\r\n\r\n";

    @Before
    public void setUp() {
        requestParser = new RequestParser();
    }

    @Test
    public void generatesStatusLineFields() throws RequestParseException {
        Request parsedRequest = requestParser.create(SIMPLE_GET);


        assertEquals(GET, parsedRequest.getRequestMethod());
        assertEquals(SIMPLE_GET_PATH, parsedRequest.getRequestPath());
        assertEquals(VERSION, parsedRequest.getRequestVersion());
    }

    @Test
    public void generatesParsedRequestWithHeaders() throws RequestParseException {
        Request parsedRequest = requestParser.create(REQUEST_WITH_HEADERS);

        assertEquals("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3)", parsedRequest.getHeaders().get("User-Agent"));
        assertEquals("isitchristmas.com", parsedRequest.getHeaders().get("Host"));
        assertEquals("Keep-Alive", parsedRequest.getHeaders().get("Connection"));

    }

    @Test
    public void throwsRequestParseExceptionWhenRequestLineIsMalformed() {
        try {
            requestParser.create(MALFORMED_REQUEST);
            assertTrue(false);
        } catch (RequestParseException e) {
            assertEquals(RequestParseException.class, e.getClass());
        }
    }
}

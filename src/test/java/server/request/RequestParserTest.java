package test.java.server.request;

import main.java.server.request.Request;
import main.java.server.request.RequestParser;
import org.junit.Before;
import org.junit.Test;


import static main.java.server.HTTPMessageComponents.HTTPMethods.GET;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.VERSION;
import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private RequestParser requestParser;

    private static final String SIMPLE_GET = "GET /simple_get HTTP/1.1\r\n";
    private static final String SIMPLE_GET_PATH = "/simple_get";

    @Before
    public void setUp() {
        requestParser = new RequestParser();
    }

    @Test
    public void generatesStatusLineFields() {
        Request parsedRequest = requestParser.create(SIMPLE_GET);


        assertEquals(GET, parsedRequest.getRequestMethod());
        assertEquals(SIMPLE_GET_PATH, parsedRequest.getRequestPath());
        assertEquals(VERSION, parsedRequest.getRequestVersion());
    }
}

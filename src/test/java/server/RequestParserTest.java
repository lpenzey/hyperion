package test.java.server;

import main.java.server.request.Request;
import main.java.server.request.RequestParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    private RequestParser requestParser;

    private static final String SIMPLE_GET = "GET /simple_get HTTP/1.1\r\n";

    @Before
    public void setUp() {
        requestParser = new RequestParser();
    }

    @Test
    public void generatesHeaderFields() throws IOException {
        Request parsedRequest = requestParser.create(SIMPLE_GET);
        HashMap<String, String> requestHeaders = parsedRequest.getHeaders();

        assertEquals("GET", requestHeaders.get("method"));
        assertEquals("/simple_get", requestHeaders.get("path"));
        assertEquals("HTTP/1.1", requestHeaders.get("version"));
    }
}

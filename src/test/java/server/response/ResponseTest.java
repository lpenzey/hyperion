package test.java.server.response;

import main.java.server.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static org.junit.Assert.assertEquals;

import static main.java.server.HTTPMessageComponents.StatusCodes.OK;

public class ResponseTest {

    @Test
    public void statusLineIsCreatedCorrectly() {
        Response response = new Response(VERSION + SP + OK + CRLF);

        assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
    }

    @Test
    public void responseCreatedWithStatusLineAndHeaders() {
        String statusLine = VERSION + SP + OK + CRLF;
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Allow:", "GET, HEAD, OPTIONS");
        Response response = new Response(statusLine, headers);

        assertEquals("HTTP/1.1 200 OK\r\n", response.getStatusLine());
        assertEquals(headers, response.getHeaders());
    }
}

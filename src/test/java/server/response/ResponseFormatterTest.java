package test.java.server.response;

import main.java.server.response.Response;
import main.java.server.response.ResponseFormatter;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.OK;

public class ResponseFormatterTest {

    @Test
    public void createsFormattedStringFromStatusLine() {
        Response response = new Response.Builder()
                .withStatus(VERSION + SP + OK + CRLF)
                .build();

        ResponseFormatter formatter = new ResponseFormatter(response);

        assertEquals(VERSION + SP + OK + CRLF + CRLF + CRLF, formatter.stringifyResponse());
    }

    @Test
    public void convertsHeaderToString() {
        Response response = new Response.Builder()
                .withStatus(VERSION + SP + OK + CRLF)
                .withHeader(ALLOW, "GET, HEAD, OPTIONS")
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withHeader(LOCATION, "http://127.0.0.1:5000/redirect")
                .build();

        ResponseFormatter formatter = new ResponseFormatter(response);

        String fullResponse = formatter.stringifyResponse();
        String expectedResponse = "HTTP/1.1 200 OK\r\nAllow:GET, HEAD, OPTIONS\r\nContent-Type:text/html; charset=utf-8\r\nLocation:http://127.0.0.1:5000/redirect\r\n\r\n";

        assertEquals(expectedResponse, fullResponse);
    }

    @Test
    public void willWorkWithPostmanRequests() {
        Response response = new Response.Builder()
                .withStatus(VERSION + SP + OK + CRLF)
                .build();

        ResponseFormatter formatter = new ResponseFormatter(response);

        String fullResponse = formatter.stringifyResponse();
        System.out.println(fullResponse);
        assertTrue(fullResponse.contains("\r\n\r\n"));
    }
}
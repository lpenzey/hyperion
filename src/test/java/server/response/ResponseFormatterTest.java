package test.java.server.response;

import main.java.server.response.Response;
import main.java.server.response.ResponseFormatter;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.OK;

public class ResponseFormatterTest {

    @Test
    public void createsFormattedStringFromStatusLine() {
        String statusLine = VERSION + SP + OK + CRLF;

        Response response = new Response(statusLine);
        ResponseFormatter formatter = new ResponseFormatter(response);

        assertEquals(statusLine, formatter.stringifyResponse());
    }

    @Test
    public void convertsHeaderToString() {
        String statusLine = VERSION + SP + OK + CRLF;
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Allow", "GET, HEAD, OPTIONS");
        headers.put("Content-Type", "text/html; charset=utf-8");

        Response response = new Response(statusLine, headers, "hey");
        ResponseFormatter formatter = new ResponseFormatter(response);

        String fullResponse = formatter.stringifyResponse();
        String expectedResponse = "HTTP/1.1 200 OK\r\nAllow:GET, HEAD, OPTIONS\r\nContent-Type:text/html; charset=utf-8\r\n\r\nhey";

        assertEquals(expectedResponse, fullResponse);
    }
}
package main.java.server.response;

import java.util.HashMap;
import java.util.stream.Collectors;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.CRLF;

public class ResponseFormatter {
    private final Response response;

    public ResponseFormatter(Response response) {
        this.response = response;
    }

    public String stringifyResponse() {
        String responseString = response.getStatusLine();
        if (response.getHeaders() != null) {
            String stringHeaders = convertHeadersToString(response.getHeaders());
            return responseString + stringHeaders + CRLF + CRLF + response.getBody();
        }

        return responseString;
    }

    private String convertHeadersToString(HashMap<String, String> headers) {
        try {
            return headers.keySet().stream()
                    .map(key -> key + ":" + headers.get(key))
                    .collect(Collectors.joining("\r\n"));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return null;
    }
}
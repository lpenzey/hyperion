package main.java.server.response;

import java.util.HashMap;

public class Response {
    private String statusLine;
    private HashMap<String, String> headers;
    private String body;

    private Response(){
    }

    public String getStatusLine() {
        return statusLine;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private Response response;
        private String statusLine;
        private HashMap<String, String> headers;
        private String body;

        public Builder(){
            this.response = new Response();
            this.headers = new HashMap<>();
            this.body = "";

        }

        public Builder withStatus(String statusLine) {
            this.statusLine = statusLine;

            return this;
        }

        public Builder withHeader(String key, String value) {
            this.headers.put(key, value);

            return this;
        }

        public Builder withBody(String body) {
            this.body = body;

            return this;
        }

        public Response build() {
            response.statusLine = this.statusLine;
            response.headers = this.headers;
            response.body = this.body;

            return response;
        }

    }

}

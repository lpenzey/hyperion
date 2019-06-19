package main.java.server.router;

public class Route {
        private String path;
        private String method;

        Route(String method, String path) {
            this.method = method;
            this.path = path;
        }

    public String getPath() {
        return path;
    }


    public String getMethod() {
        return method;
    }

}

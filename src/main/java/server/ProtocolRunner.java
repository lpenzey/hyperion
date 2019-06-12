package main.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

import main.java.server.request.Request;
import main.java.server.request.RequestParser;
import main.java.server.router.ServerRouter;


class ProtocolRunner implements Runnable {
    private final RequestParser requestParser = new RequestParser();
    private final ServerRouter router = new ServerRouter();
    private final Client client;
    private final BufferedReader in;
    private final PrintWriter out;

    public ProtocolRunner(Client client) {
        this.client = client;
        this.in = client.getReader();
        this.out = client.getWriter();
    }

    public void run() {
        try {
            String input = in.readLine();
            while(input != null) {
                Request request = requestParser.create(input);
                Response response = router.route(request);
                out.print(response.getStatusLine());
                out.flush();
                client.close();
            }
        } catch(IOException error) {
            ServerLogger.serverLogger.log(Level.WARNING, "Error: " + error);
        }
    }
}

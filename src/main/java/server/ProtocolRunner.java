package main.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

import main.java.server.request.Request;
import main.java.server.request.RequestParser;


class ProtocolRunner implements Runnable {
    private final RequestParser requestParser = new RequestParser();
    private final Router router = new Router();
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
                String response = router.route(request);
                out.print(response);
                out.flush();
                client.close();
            }
        } catch(IOException error) {
            ServerLogger.serverLogger.log(Level.WARNING, "Error: " + error);        }
    }
}

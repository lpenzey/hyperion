package main.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

import main.java.application.App;
import main.java.server.request.Request;
import main.java.server.request.RequestParseException;
import main.java.server.request.RequestParser;
import main.java.server.response.PathFinder;
import main.java.server.response.Response;
import main.java.server.response.ResponseFormatter;


class ProtocolRunner implements Runnable {
    private final RequestParser requestParser = new RequestParser();
    private final Client client;
    private final BufferedReader in;
    private final PrintWriter out;
    private final App app;

    public ProtocolRunner(Client client, App app) {
        this.app = app;
        this.client = client;
        this.in = client.getReader();
        this.out = client.getWriter();
    }

    public void run() {
        try {
            String input = in.readLine();
            while(input != null) {
                Request request = requestParser.create(input);
                PathFinder pathFinder = new PathFinder(request, app);
                Response response = pathFinder.selectHandler();
                ResponseFormatter formatter = new ResponseFormatter(response);

                out.print(formatter.stringifyResponse());
                out.flush();
                client.close();
            }
        } catch(IOException | RequestParseException error) {
            ServerLogger.serverLogger.log(Level.WARNING, "Error: " + error);
        }
    }
}

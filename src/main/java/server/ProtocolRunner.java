package main.java.server;

import java.io.PrintWriter;
import java.util.logging.Level;

import main.java.application.App;
import main.java.server.request.Request;
import main.java.server.request.RequestParseException;
import main.java.server.request.RequestParser;
import main.java.server.response.PathFinder;
import main.java.server.response.Response;
import main.java.server.response.ResponseFormatter;


public class ProtocolRunner implements Runnable {
    private final RequestParser requestParser = new RequestParser();
    private final Client client;
    private final PrintWriter out;
    private final App app;

    ProtocolRunner(Client client, App app) {
        this.app = app;
        this.client = client;
        this.out = client.getWriter();
    }

    public void run() {
        try {
            String input = client.chunkStream();
            PathFinder pathFinder = new PathFinder(app);
            if(input != null) {
                Request request = requestParser.create(input);
                Response response = pathFinder.getResponse(request);
                ResponseFormatter formatter = new ResponseFormatter(response);

                out.print(formatter.stringifyResponse());
                out.flush();
                client.close();
            }
        } catch(RequestParseException error) {
            ServerLogger.serverLogger.log(Level.WARNING, "Error: " + error);
        }
    }
}

package main.java.server;

import java.io.PrintWriter;
import java.util.logging.Level;

import main.java.server.request.Request;
import main.java.server.request.RequestParseException;
import main.java.server.request.RequestParser;
import main.java.server.response.Response;
import main.java.server.response.ResponseFormatter;


public class ProtocolRunner implements Runnable {
    private final RequestParser requestParser = new RequestParser();
    private final Client client;
    private final PrintWriter out;

    private final Router router;

    ProtocolRunner(Client client, Router router) {
        this.client = client;
        this.out = client.getWriter();
        this.router = router;
    }

    public void run() {
        try {
            String input = client.chunkStream();

            if(input != null) {
                Request request = requestParser.create(input);
                Response response = router.generateResponse(request);
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

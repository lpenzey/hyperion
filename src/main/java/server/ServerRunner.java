package main.java.server;

import java.io.PrintWriter;
import java.util.logging.Level;

import main.java.server.request.Request;
import main.java.server.request.RequestParseException;
import main.java.server.request.RequestParser;
import main.java.server.response.Response;
import main.java.server.response.ResponseFormatter;
import main.java.server.response.ResponseTypes;

import static main.java.server.HTTPMessageComponents.HTTPSyntax.*;
import static main.java.server.HTTPMessageComponents.StatusCodes.INTERNAL_ERROR;


public class ServerRunner implements Runnable {
    private final RequestParser requestParser = new RequestParser();
    private final Client client;
    private final PrintWriter out;
    private final Router router;

    public ServerRunner(Client client, Router router) {
        this.client = client;
        this.out = client.getWriter();
        this.router = router;
    }

    public void run() {
            String input = client.chunkStream();
        try {
            if(!input.isEmpty()) {
                System.out.println("Client request received on "+ Thread.currentThread().getName());

                Request request = requestParser.create(input);
                Response response = router.generateResponse(request);
                ResponseFormatter formatter = new ResponseFormatter(response);

                out.print(formatter.stringifyResponse());

                System.out.println("Response sent\n");
                System.out.println("Listening for connection...");

            }
        } catch(RequestParseException error) {
            ServerLogger.serverLogger.log(Level.WARNING, "Error: " + error);
            out.print(internalErrorResponse());
        }
        out.flush();
        client.close();
    }

    private String internalErrorResponse(){
        new ResponseTypes();
        Response internalError = ResponseTypes.internalError();
        ResponseFormatter formatter = new ResponseFormatter(internalError);
        return formatter.stringifyResponse();
    }
}

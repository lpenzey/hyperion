package main.java.server;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;

public class Client {

    private final Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
    }

    public BufferedReader getReader() { return reader; }

    public PrintWriter getWriter() { return writer; }

    public void close() {
        try {
            clientSocket.close();
        } catch(IOException error) {
            ServerLogger.serverLogger.log(Level.WARNING, "Error: " + error);
        }
    }
}
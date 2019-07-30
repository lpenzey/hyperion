package main.java.server;

import main.java.server.request.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProxyHandler {

    public static String sendRequest(Request request) throws IOException {
        String path = request.getRequestPath().substring(1);

        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return content.toString();
    }
}

package test.java.server;

import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

class SocketStub extends Socket {
    private final OutputStream outputStream;
    private final InputStream inputStream;

    SocketStub(String inputStub) {
        this.outputStream = new ByteArrayOutputStream();
        this.inputStream = new ByteArrayInputStream(inputStub.getBytes());
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    public String printSentData() {
        return outputStream.toString();
    }
}

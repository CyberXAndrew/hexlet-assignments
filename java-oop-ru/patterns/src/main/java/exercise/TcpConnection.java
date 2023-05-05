package exercise;
import java.util.List;
import java.util.ArrayList;

import exercise.connections.Connection;
import exercise.connections.Connected;
import exercise.connections.Disconnected;
// BEGIN
public class TcpConnection {
    private String ip;
    private int port;
    private Connection currentConnection;
    private List<String> buffer = new ArrayList<>();

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.currentConnection = new Disconnected(this);
    }

    public void setState(Connection newConnection) {
        this.currentConnection = newConnection;
    }

    public String getCurrentState() {
        return this.currentConnection.getCurrentState();
    }
    public void connect() {
        this.currentConnection.connect();
    }
    public void disconnect() {
        this.currentConnection.disconnect();
    }
    public void write(String text) {
        if (currentConnection.getCurrentState().equals("connected")) {
            this.buffer.add(text);
        } else {
            System.out.println("Error! State: disconnected!");
        }
    }
}
// END

package exercise.connections;

import exercise.TcpConnection;
// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcp;

    public Disconnected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    public String getCurrentState() {
        return "disconnected";
    }
    public void connect() {
        this.tcp.setState(new Connected(this.tcp));
    }
    public void disconnect() {
        System.out.println("Error! Already disconnected");
    }
}
// END

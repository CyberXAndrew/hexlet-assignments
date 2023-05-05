package exercise.connections;

import exercise.TcpConnection;
// BEGIN
public class Connected implements Connection {
    private TcpConnection tcp;

    public Connected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    public String getCurrentState() {
        return "connected";
    }
    public void connect() {
        System.out.println("Error! Already connected");
    }
    public void disconnect() {
//        System.out.println("error: already disconnected");
        this.tcp.setState(new Disconnected(this.tcp));
    }
}
// END

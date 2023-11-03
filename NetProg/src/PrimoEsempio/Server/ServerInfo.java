package PrimoEsempio.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerInfo {
    public static String serverAddress = "localhost";
    public static int serverPort = 80;

    public static int findFirstFreePort() {
        int port = 0;
        while (port <= 65536) {
            try {
                ServerSocket tempServer = new ServerSocket(port);
                tempServer.close();
                return port;
            } catch (IOException e) {
                port++;
            }
        }
        return -1;
    }
}

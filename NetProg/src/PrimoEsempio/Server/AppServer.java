package PrimoEsempio.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {

    public void start() {
        try (ServerSocket server = new ServerSocket(ServerInfo.serverPort)) {
            System.out.println("Server aperto su porta: " + server.getLocalPort());
            while (true) {
                Socket client = server.accept();
                new Thread(new ClientHandler(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {

        private Socket client;
        private PrintWriter outToClient;
        private BufferedReader inFromClient;

        public ClientHandler(Socket client) {
            this.client = client;
            getClientStreams();
        }

        private void getClientStreams() {
            try {
                outToClient = new PrintWriter(client.getOutputStream(), true);
                inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            } catch (IOException e) {
                System.out.println("[ClientHandler]: can't get client streams");
            }
        }

        @Override
        public void run() {
            System.out.println("[ServerThread]: New Client> " + client.getInetAddress());
            outToClient.println("Benvenuto al server!");
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("[ServerThread]: Error while closing connection with client");
            }
        }
    }
}

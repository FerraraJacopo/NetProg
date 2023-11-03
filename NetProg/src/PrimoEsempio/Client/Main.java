package PrimoEsempio.Client;

import PrimoEsempio.Server.ServerInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        final int port = ServerInfo.serverPort;
        final String address = ServerInfo.serverAddress;
        try (Socket client = new Socket(address, port)) {
            System.out.println("Connesso su porta: " + port);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String valueFromServer = bufferedReader.readLine();
            System.out.println(valueFromServer);
        } catch (UnknownHostException e) {
            System.out.println(address + " unknown");
            System.exit(0);
        } catch (ConnectException e) {
            System.err.println("Server non attivo");
            //provate a riconnetervi in N secondi
        } catch (IOException e) {
            System.out.println("Can't open connection");
            System.exit(0);
        }
    }
}

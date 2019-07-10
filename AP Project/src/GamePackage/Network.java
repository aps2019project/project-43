package GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {
    private Socket socket;
    private ServerSocket serverSocket;

    Network(ServerInfo server, int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            server.addClient(socket);
        }
    }
}

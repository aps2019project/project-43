package GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    static Network network;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        ServerInfo server = new ServerInfo();
        int port = 8000;
        network = new Network(server, port);
    }

}

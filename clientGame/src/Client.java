import GamePackage.NetworkObject;

import java.io.EOFException;
import java.util.Scanner;

public class Client {

    private static String ip = "localhost";
    private static int port = 8000;
    private static Network network = new Network(ip, port);
    private static Scanner scanner = new Scanner(System.in);
    private static String authToken = null;

    private static Thread senderThread=new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                String command = scanner.nextLine();
                network.send(new NetworkObject(NetworkObject.Type.COMMAND, 0, command, authToken));
            }
        }
    });

    private static Thread receiverThread=new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                NetworkObject response = null;
                try {
                    response = network.receive();
                } catch (EOFException e) {
                    e.printStackTrace();
                    return;
                }
                if(response != null) processResponse(response);
            }
        }
    });

    public static void main(String[] args) {
        senderThread.start();
        receiverThread.start();
    }

    private static void processResponse(NetworkObject response){
        switch (response.getType()){
            case EXIT:
                network.end();
                senderThread.interrupt();
                receiverThread.interrupt();
                break;
            case LOGIN:
                authToken = response.getAuthToken();
                break;
            case LOGOUT:
                authToken = null;
                break;
            case PRINT:
                System.out.println(response.getCommand());
                break;
        }
    }
}

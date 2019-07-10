import GamePackage.NetworkObject;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    Network(String serverIP, int port) {
        try {
            socket = new Socket(serverIP, port);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(NetworkObject runnable) {
        try {
            output.writeObject(runnable);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NetworkObject receive() throws EOFException {
        NetworkObject sender=null;
        try {
            sender=(NetworkObject) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if(e instanceof EOFException) throw (EOFException)e;
            e.printStackTrace();
        }
        return sender;
    }

    public void end(){
        try {
            output.close();
            input.close();
            socket.close();
            System.exit(-1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

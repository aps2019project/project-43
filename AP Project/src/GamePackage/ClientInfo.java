package GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ClientInfo extends Thread {
    GameMenu accountMenu=new AccountMenu(this);
    GameMenu battleMenu=new BattleMenu(this);
    GameMenu collectionMenu=new CollectionMenu(this);
    GameMenu mainMenu=new MainMenu(this);
    GameMenu shopMenu=new ShopMenu(this);
    private GameMenu currentMenu;
    private Account loggedAccount;
    private int clientId;
    private Set<PlayMenu> playRequests = new HashSet<>();
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    ServerInfo server;

    public void addToPlayRequests(PlayMenu playMenu){
        playRequests.add(playMenu);
    }

    public void removePlayRequest(PlayMenu playMenu){
        playRequests.remove(playMenu);
    }

    public void removePlayRequest(ClientInfo client){
        playRequests.removeIf(playMenu -> playMenu.getOpponent().getClient()==client);
    }

    public boolean hasPlayRequest(ClientInfo client){
        for(PlayMenu playMenu:playRequests){
            if(playMenu.getOpponent().getClient()==client){
                return true;
            }
        }
        return false;
    }

    public void acceptPlayRequest(ClientInfo client){
        for(PlayMenu playMenu:playRequests){
            if(playMenu.getOpponent().getClient()==client){
                playMenu.setCurrentMenu();
            }
        }
    }

    ClientInfo(ServerInfo server, int clientId, Socket clientSocket){
        this.server = server;
        this.clientId = clientId;
        this.socket = clientSocket;
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ClientInfo(){

    }

    public int getClientId() {
        return clientId;
    }

    Account getLoggedAccount(){
        return loggedAccount;
    }

    public void setLoggedAccount(Account loggedAccount) {
        if(this.loggedAccount!=null) {
            this.loggedAccount.setClient(null);
        }
        if(loggedAccount!=null){
            loggedAccount.setClient(this);
        }
        this.loggedAccount = loggedAccount;
    }

    void setCurrentMenu(GameMenu currentMenu) {
        this.currentMenu = currentMenu;
        currentMenu.showMenu();
    }

    boolean exec(String command) throws Exception {
        return currentMenu.execCommand(command);
    }

    public void run() {
        accountMenu.setCurrentMenu();
        while (true) {
            NetworkObject command = receive();
            if(command==null) {
                end();
                return;
            }
            switch (command.getType()) {
                case COMMAND:
                    if(command.getAuthToken()!=null){
                        Account user = server.getUserByToken(command.getAuthToken());
                        if(user==null){
                            sendPrint("Invalid token");
                            sendSwitch();
                            break;
                        }
                        if(user.getClient()!=null&&user.getClient()!=this){
                            sendPrint("Account is being used by another client");
                            sendSwitch();
                            break;
                        }
                        setLoggedAccount(user);
                    } else {
                        setLoggedAccount(null);
                    }
                    try {
                        if(!exec(command.getCommand())){
                            end();
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    sendSwitch();
                    break;
            }
        }
    }

    public void sendWaitOrCancel(){
        send(new NetworkObject(NetworkObject.Type.WAIT_OR_CANCEL, 0 , null, null));
    }

    public void sendFriendJoined(){
        send(new NetworkObject(NetworkObject.Type.FRIEND_JOINED, 0 , null, null));
    }

    public void sendCancelWaiting(){
        send(new NetworkObject(NetworkObject.Type.CANCEL_WAIT, 0 , null, null));
    }

    public void sendLogin(String authToken){
        send(new NetworkObject(NetworkObject.Type.LOGIN, 0, null, authToken));
    }

    public void sendLogout(){
        send(new NetworkObject(NetworkObject.Type.LOGOUT, 0, null, null));
    }

    private void sendExit(){
        send(new NetworkObject(NetworkObject.Type.EXIT, 0, null, null));
    }

    public void sendPrint(String string){
        send(new NetworkObject(NetworkObject.Type.PRINT, 0, string, null));
    }
    public void sendPrint(){
        send(new NetworkObject(NetworkObject.Type.PRINT, 0, "", null));
    }

    private void sendSwitch(){
        send(new NetworkObject(NetworkObject.Type.SWITCH, 0, null, null));
    }

    public void sendChat(){
        send(new NetworkObject(NetworkObject.Type.CHAT, 0, null, null));
    }

    public void sendEndChat(){
        send(new NetworkObject(NetworkObject.Type.END_CHAT, 0, null, null));
    }

    void send(NetworkObject runnable) {
        try {
            output.writeObject(runnable);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private NetworkObject receive() {
        NetworkObject sender=null;
        try {
            sender=(NetworkObject) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sender;
    }

    private void end(){
        setLoggedAccount(null);
        sendExit();
        try {
            output.close();
            input.close();
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        server.removeClient(this);
        System.out.println("Client "+this+" disconnected ..");
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof ClientInfo && ((ClientInfo) o).clientId==clientId;
    }

    @Override
    public String toString() {
        return "" + clientId;
    }
}

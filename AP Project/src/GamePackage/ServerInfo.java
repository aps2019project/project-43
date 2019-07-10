package GamePackage;

import java.net.Socket;
import java.util.*;

public class ServerInfo {

    private int LAST_CLIENT_ID = 0;
    private List<ClientInfo> clients = new ArrayList<>();
    private HashMap<Integer, ClientInfo> clientsMap = new HashMap<>();
    private List<Account> accounts = new ArrayList<>();
    private HashMap<String, Account> userMap = new HashMap<>();
    private Shop shop = new Shop();
    private ArrayList<String> chats = new ArrayList<>();
    private Set<ClientInfo> chatting = new HashSet<>();

    public void attendChatroom(ClientInfo client){
        chatting.add(client);
    }

    public void leaveChatroom(ClientInfo client){
        chatting.remove(client);
    }

    public void addChat(String str){
        chats.add(str);
        for (ClientInfo client : chatting) {
            client.sendPrint(str);
        }
    }

    public void sendChats(ClientInfo client){
        for (String chat : chats) {
            client.sendPrint(chat);
        }
    }

    public Shop getShop(){
        return shop;
    }

    String showLeaderboard() {
        return (new Leaderboard(accounts).sortByWins()).toString();
    }

    public Account getUser(String name) {
        return userMap.get(name);
    }

    public Account getOnlineUser(String name) {
        for (ClientInfo client: clients) {
            if(client.getLoggedAccount()!=null&&client.getLoggedAccount().getUsername().equals(name)) return client.getLoggedAccount();
        }
        return null;
    }


    public ClientInfo getClient(int clientId) {
        return clientsMap.get(clientId);
    }

    public void addClient(Socket clientSocket){
        LAST_CLIENT_ID++;
        ClientInfo client=new ClientInfo(this,LAST_CLIENT_ID, clientSocket);
        clients.add(client);
        clientsMap.put(LAST_CLIENT_ID, client);
        client.start();
        System.out.println("Client "+client+" is connected ..");
    }

    public void printUsers(ClientInfo client) {
        int count = 1;
        client.sendPrint("*****Users List*****");
        for (String key: userMap.keySet()) {
            client.sendPrint(count++ + ". " + key);
        }
    }

    public void printOnlineUsers(ClientInfo client) {
        int count = 1;
        client.sendPrint("*****Users List*****");
        for (ClientInfo c: clients) {
            if(c!=client&&c.getLoggedAccount()!=null) {
                client.sendPrint(count++ + ". " + c.getLoggedAccount().getUsername() + (client.hasPlayRequest(c)?" (has requested)":""));
            }
        }
    }

    void createAccount(ClientInfo client, String userName, String password) {
        if (userMap.containsKey(userName)) {
            client.sendPrint("This Username Already Exists");
            return;
        }
        Account newAccount = new Account(userName, password);
        accounts.add(newAccount);
        userMap.put(userName, newAccount);
        client.sendPrint("Your account created successfully ");
        login(client, userName, password);
    }

    void login(ClientInfo client, String username, String password) {
        if (!userMap.containsKey(username)) {
            client.sendPrint("this username doesn't exist");
            return;
        }
        Account acc = userMap.get(username);
        if (!acc.checkPassword(password)) {
            client.sendPrint("Incorrect Password");
            return;
        }
        acc.updateToken();
        client.sendLogin(acc.getAuthToken());
        client.sendPrint("You're logged in");
        client.setLoggedAccount(acc);
        client.accountMenu.setCurrentMenu();
    }

    Account getUserByToken(String authToken){
        for (Account user : accounts) {
            if(user.checkToken(authToken)){
                return user;
            }
        }
        return null;
    }

    void logout(ClientInfo client) {
        if (client.getLoggedAccount() != null) {
            client.getLoggedAccount().setClient(null);
            client.setLoggedAccount(null);
            client.sendLogout();
            client.sendPrint("You're successfully logged out");
            client.accountMenu.setCurrentMenu();
        } else
            client.sendPrint("You're not even logged in");
    }

    public void removeClient(ClientInfo client) {
        clients.remove(client);
        clientsMap.remove(client.getClientId());
        leaveChatroom(client);
        for (ClientInfo clientInfo : clients) {
            clientInfo.removePlayRequest(client);
        }
    }
}

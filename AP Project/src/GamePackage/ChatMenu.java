package GamePackage;

public class ChatMenu extends GameMenu {
    private GameMenu formerMenu;

    ChatMenu(ClientInfo client, GameMenu formerMenu) {
        super(client);
        this.formerMenu=formerMenu;
        client.server.sendChats(client);
        client.server.attendChatroom(client);
    }

    @Override
    boolean execCommand(String input) {
        String inp = input.trim().toLowerCase();
        switch (inp){
            case "exit":
                client.sendEndChat();
                formerMenu.setCurrentMenu();
                client.server.leaveChatroom(client);
                break;
            case "help":
                showMenu();
                break;
            default:
                client.server.addChat(client.getLoggedAccount()+": "+input);
        }
        return true;
    }

    @Override
    void showMenu() {
        client.sendPrint("***** Chatroom *****");
        client.sendPrint("1. Type anything to send");
        client.sendPrint("2. Help");
        client.sendPrint("3. Exit");
    }

}

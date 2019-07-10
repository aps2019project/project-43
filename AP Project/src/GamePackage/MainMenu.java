package GamePackage;

public class MainMenu extends GameMenu {

    MainMenu(ClientInfo client) {
        super(client);
    }

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        switch (input) {
            case "battle":
                if (client.getLoggedAccount().getMainDeck()!= null && client.getLoggedAccount().getMainDeck().validate()) client.battleMenu.setCurrentMenu();
                else client.sendPrint("selected deck is invalid");
                break;
            case "shop":
                client.shopMenu.setCurrentMenu();
                break;
            case "collection":
                client.collectionMenu.setCurrentMenu();
                break;
            case "help":
                showMenu();
                break;
            case "exit":
                client.accountMenu.setCurrentMenu();
                break;
            default:
                client.sendPrint("Invalid Command\n");
                showMenu();
        }
        return true;
    }

    void showMenu() {
        client.sendPrint("*****Main Menu*****");
        client.sendPrint("1. Battle");
        client.sendPrint("2. Collection");
        client.sendPrint("3. Shop");
        client.sendPrint("4. Help (show menu)");
        client.sendPrint("5. Exit");
    }

}

package GamePackage;

public class ShopMenu extends GameMenu {

    ShopMenu(ClientInfo client) {
        super(client);
    }

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        String[] inputArray = input.split(" ");
        Shop playersShop = client.server.getShop();
        switch (inputArray[0]) {
            case "show":
                if (inputArray.length > 1) {
                    if (inputArray[1].equalsIgnoreCase("collection")) {
                        client.sendPrint(client.getLoggedAccount().getCollection().show());
                    }
                } else {
                    client.sendPrint(playersShop.show());
                }
                break;
            case "search":
                if (inputArray[1].equalsIgnoreCase("collection")) {
                    client.sendPrint(client.getLoggedAccount().getCollection().search(inputArray[2]));
                } else {
                    client.sendPrint(playersShop.search(inputArray[1]));
                }
                break;
            case "sell":
                for (int i = 1; i < inputArray.length; i++) {
                    playersShop.sellCard(client,Integer.parseInt(inputArray[i]));
                }
                break;
            case "buy":
                playersShop.buyCard(client, inputArray[1]);
                break;
            case "help":
                showMenu();
                break;
            case "exit":
                client.mainMenu.setCurrentMenu();
                break;
            default:
                client.sendPrint("Invalid Command\n");
                showMenu();
        }
        return true;
    }

    void showMenu() {
        client.sendPrint("1. Show");
        client.sendPrint("2. Show collection");
        client.sendPrint("3. Search[item name|card name]");
        client.sendPrint("4. Search collection[item name|card name]");
        client.sendPrint("5. Buy[card name|item name]");
        client.sendPrint("6. Sell[card ID]");
        client.sendPrint("8. Help (show menu)");
        client.sendPrint("7. Exit");
    }

}

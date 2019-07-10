package GamePackage;

public class CollectionMenu extends GameMenu {

    CollectionMenu(ClientInfo client) {
        super(client);
    }

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        String[] inputArray = input.split(" ");
        switch (inputArray[0]) {
            case "search":
                client.sendPrint(client.getLoggedAccount().getCollection().search(inputArray[1]));
                break;
            case "create":
                client.getLoggedAccount().createDeck(inputArray[2]);
                break;
            case "delete":
                client.getLoggedAccount().deleteDeck(inputArray[2]);
                break;
            case "add":
                client.getLoggedAccount().addObjectToDeck(inputArray[1], inputArray[4]);
                break;
            case "remove":
                client.getLoggedAccount().removeObjectFromDeck(inputArray[1], inputArray[4]);
                break;
            case "validate":
                client.getLoggedAccount().validateDeck(inputArray[2]);
                break;
            case "select":
                client.getLoggedAccount().setMainDeck(inputArray[2]);
                break;
            case  "show":
                if (inputArray.length>1){
                    if(inputArray[1].equalsIgnoreCase("all")) {
                        client.getLoggedAccount().showAllDecks();
                    }else{
                        client.getLoggedAccount().showDeck(inputArray[2]);
                    }
                } else {
                    client.sendPrint(client.getLoggedAccount().getCollection().show()); }
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
        client.sendPrint("2. Search [card name | item name ]");
        client.sendPrint("3. Create deck [deck name]");
        client.sendPrint("4. Delete deck [deck name]");
        client.sendPrint("5. Add [card id | card id | hero id] to deck [deck name]");
        client.sendPrint("6. Remove [card id | card id | hero id] to deck [deck name]");
        client.sendPrint("7. Validate deck [deck name]");
        client.sendPrint("8. Select deck [deck name]");
        client.sendPrint("9. Show all decks");
        client.sendPrint("10. Show deck [deck name]");
        client.sendPrint("11. Help (show menu)");
        client.sendPrint("12. Exit");
    }

}


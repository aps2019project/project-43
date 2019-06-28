package GamePackage;

public class CollectionMenu extends GameMenu {

    private static CollectionMenu collectionMenu = new CollectionMenu();

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        String[] inputArray = input.split(" ");
        switch (inputArray[0]) {
            case "search":
                CollectionMenuFunctions.SEARCH.doSomething(inputArray);
                break;
            case "save":
                CollectionMenuFunctions.SAVE.doSomething(inputArray);
                break;
            case "create":
                CollectionMenuFunctions.CREATE_DECK.doSomething(inputArray);
                break;
            case "delete":
                CollectionMenuFunctions.DELETE_DECK.doSomething(inputArray);
                break;
            case "add":
                CollectionMenuFunctions.ADD.doSomething(inputArray);
                break;
            case "remove":
                CollectionMenuFunctions.REMOVE.doSomething(inputArray);
                break;
            case "validate":
                CollectionMenuFunctions.VALIDATE_DECK.doSomething(inputArray);
                break;
            case "select":
                CollectionMenuFunctions.SELECT_DECK.doSomething(inputArray);
                break;
            case  "show":
                if (inputArray.length>1){
                    if(inputArray[1].equalsIgnoreCase("all")) {
                        CollectionMenuFunctions.SHOW_ALL_DECKS.doSomething(inputArray);
                    }else{
                        CollectionMenuFunctions.SHOW_DECK.doSomething(inputArray);
                    }
                } else {
                    CollectionMenuFunctions.SHOW.doSomething(inputArray); }
                break;
            case "help":
                showMenu();
                break;
            case "exit":
                MainMenu.goToMainMenu();
                break;
            default:
                System.out.println("Invalid Command");
        }
        return true;
    }

    void showMenu() {
        System.out.println("1. Show");
        System.out.println("2. Save");
        System.out.println("3. Search [card name | item name ]");
        System.out.println("4. Create deck [deck name]");
        System.out.println("5. Delete deck [deck name]");
        System.out.println("6. Add [card id | card id | hero id] to deck [deck name]");
        System.out.println("7. Remove [card id | card id | hero id] to deck [deck name]");
        System.out.println("8. Validate deck [deck name]");
        System.out.println("9. Select deck [deck name]");
        System.out.println("10. Show all decks");
        System.out.println("11. Show deck [deck name]");
        System.out.println("12. Help (show menu)");
        System.out.println("13. Exit");
    }

    public static void goToCollectionMenu(){
        Main.setCurrentMenu(collectionMenu);
        collectionMenu.showMenu();
    }
}


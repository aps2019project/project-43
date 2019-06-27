package GamePackage;

public class CollectionMenu extends GameMenu {

    private static GameMenu collectionMenu = new CollectionMenu();
    private String[] inputArray;

    private CollectionMenu(){

    }

    public static GameMenu getCollectionMenu(){
        return collectionMenu;
    }

    @Override
    public void setState(String input) {
        boolean showMenu = true;
        input = input.trim().toLowerCase();
        inputArray = input.split(" ");
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
                if (inputArray.length>1 && inputArray[1].equalsIgnoreCase("all")) {
                    CollectionMenuFunctions.SHOW_ALL_DECKS.doSomething(inputArray);
                } else {CollectionMenuFunctions.SHOW.doSomething(inputArray); }
                break;
            case "exit":
                showMenu = false;
                MainMenu.goToMainMenu();
                break;
            default: {
                System.out.println("Invalid Command");
            }
        }
    if(showMenu) showMenu();
    }

    private void showMenu() {
        System.out.print("1. Show\n" +
                "2. Save\n" +
                "3. Search [card name | item name ]\n" +
                "4. Create deck [deck name]\n" +
                "5. Delete deck [deck name]\n" +
                "6. Add [card id | card id | hero id] to deck [deck name]\n" +
                "7. Remove [card id | card id | hero id] to deck [deck name]\n" +
                "8. Validate deck [deck name]\n" +
                "9. Select deck [deck name]\n" +
                "10. Show all decks\n" +
                "11. Show deck [deck name]\n" +
                "12. Help\n" +
                "13. Exit\n");
    }

    public static void goToCollectionMenu(){
        GameMenu.setCurrentMenu(CollectionMenu.getCollectionMenu());
        ((CollectionMenu) collectionMenu).showMenu();
    }
}


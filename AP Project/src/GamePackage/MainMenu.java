package GamePackage;

public class MainMenu extends GameMenu {

    private static GameMenu mainMenu = new MainMenu();

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        switch (input) {
            case "battle":
                if (Account.getLoggedAccount().getMainDeck()!= null && Account.getLoggedAccount().getMainDeck().validate()) BattleMenu.goToBattleMenu();
                else System.out.println("selected deck is invalid");
                break;
            case "shop":
                ShopMenu.goToShopMenu();
                break;
            case "collection":
                CollectionMenu.goToCollectionMenu();
                break;
            case "help":
                showMenu();
                break;
            case "exit":
                AccountMenu.goToAccountMenu();
                break;
            default:
                System.out.println("Invalid Command");
        }
        return true;
    }

    void showMenu() {
        System.out.println("*****Main Menu*****");
        System.out.println("1. Battle");
        System.out.println("2. Collection");
        System.out.println("3. Shop");
        System.out.println("4. Help (show menu)");
        System.out.println("5. Exit");
    }

    public static void goToMainMenu() {
        Main.setCurrentMenu(mainMenu);
    }
}

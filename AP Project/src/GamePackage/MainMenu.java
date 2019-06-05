package GamePackage;

public class MainMenu extends GameMenu {

    private static GameMenu mainMenu = new MainMenu();


    private MainMenu() {

    }

    public static GameMenu getMainMenu() {
        return mainMenu;
    }

    @Override
    public void setState(String input) {
        input = input.trim();
        switch (input) {
            case "battle": {
                BattleMenu.goToBattleMenu();
                break;
            }
            case "shop": {
                ShopMenu.goToShopMenu();
                break;
            }
            case "collection": {
                CollectionMenu.goToCollectionMenu();
                break;
            }
            case "exit": {
                AccountMenu.goToAccountMenu();
                break;
            }
            case "help": {
                showMenu();
                break;
            }
            default: {

            }
        }
    }

    private void showMenu() {
        System.out.print("*****Main Menu*****\n" +
                "1. Battle\n" +
                "2. Collection\n" +
                "3. Shop\n" +
                "4. Help\n" +
                "5. Exit\n");

    }

    public static void goToMainMenu() {
        GameMenu.setCurrentMenu(MainMenu.getMainMenu());
        ((MainMenu) mainMenu).showMenu();
    }
}

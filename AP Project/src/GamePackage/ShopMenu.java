package GamePackage;

public class ShopMenu extends GameMenu {

    private static GameMenu shopMenu = new ShopMenu();

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        String[] inputArray = input.split(" ");
        Shop playersShop = Account.getLoggedAccount().getShop();
        switch (inputArray[0]) {
            case "show":
                if (inputArray.length > 1) {
                    if (inputArray[1].equalsIgnoreCase("collection")) {
                        Account.getLoggedAccount().getCollection().print();
                    }
                } else {
                    playersShop.show();
                }
                break;
            case "search":
                if (inputArray[1].equalsIgnoreCase("collection")) {
                    Account.getLoggedAccount().getCollection().search(inputArray[2]);
                } else {
                    playersShop.search(inputArray[1]);
                }
                break;
            case "sell":
                for (int i = 1; i < inputArray.length; i++) {
                    playersShop.sellCard(Integer.parseInt(inputArray[i]));
                }
                break;
            case "buy":
                playersShop.buyCard(inputArray[1]);
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
        System.out.println("2. Show collection");
        System.out.println("3. Search[item name|card name]");
        System.out.println("4. Search collection[item name|card name]");
        System.out.println("5. Buy[card name|item name]");
        System.out.println("6. Sell[card ID]");
        System.out.println("8. Help (show menu)");
        System.out.println("7. Exit");
    }

    public static void goToShopMenu() {
        Main.setCurrentMenu(shopMenu);
    }

}

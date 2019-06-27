package GamePackage;

public class ShopMenu extends GameMenu {

    private static GameMenu shopMenu = new ShopMenu();

    private ShopMenu() {

    }

    public static GameMenu getShopMenu() {
        return shopMenu;
    }

    @Override
    public void setState(String input) {
        input = input.trim().toLowerCase();
        String[] command = input.split(" ");
        AccountMenu accountMenu = (AccountMenu) AccountMenu.getAccountMenu();
        Shop playersShop = AccountMenu.getLoggedAccount().getShop();
        switch (command[0]) {
            case "show": {
                if (command.length > 1) {
                    if (command[1].equalsIgnoreCase("collection")) {
                        playersShop.showCollection();
                    }
                } else {
                    playersShop.show();
                }
                break;
            }
            case "search": {
                if (command[1].equalsIgnoreCase("collection")) {
                    playersShop.searchCollection(command[2]);
                } else {
                    playersShop.search(command[1]);
                }
                break;
            }
            case "sell": {
                for (int i = 1; i < command.length; i++) {
                    playersShop.sellCard(Integer.parseInt(command[i]));
                }
                break;
            }
            case "buy": {
                playersShop.buyCard(command[1]);
                break;
            }
            case "exit": {
                MainMenu.goToMainMenu();
                break;
            }
            case "help": {
                showMenu();
                break;
            }
            default: {
                System.out.println("invalid Command");
                break;
            }
        }
    }

    private void showMenu() {
        System.out.print("1. Show\n" +
                "2. Show collection\n" +
                "3. Search[item name|card name]\n" +
                "4. Search collection[item name|card name]\n" +
                "5. Buy[card name|item name]\n" +
                "6. Sell[card ID]\n" +
                "7. Exit\n" +
                "8. help\n");
    }

    public static void goToShopMenu() {
        GameMenu.setCurrentMenu(ShopMenu.getShopMenu());
        ((ShopMenu) shopMenu).showMenu();
    }

}

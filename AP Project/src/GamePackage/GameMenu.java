package GamePackage;

import javafx.scene.Group;

public class GameMenu extends Group {
    public static final double MENU_WIDTH = 400;
    public static final double MENU_HEIGHT = 500;



    private static GameMenu currentMenu = AccountMenu.getAccountMenu();

    public static GameMenu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(GameMenu currentMenu) {
        GameMenu.currentMenu = currentMenu;
    }

    public void setState(String input) {
        System.out.println("You're stuck in gameMenu!");
    }

    private void showMenu() {

    }

}

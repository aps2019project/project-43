package GamePackage;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class GameMenu extends StackPane {
    protected static final double MENU_WIDTH = 400;
    protected static final double MENU_HEIGHT = 500;
    protected static final double BUTTON_WIDTH = 200;
    protected static final double FIRST_BUTTON_HEIGHT = 100;
    protected static final double BUTTON_PREF_WIDTH = 400;
    protected static final double BUTTON_PREF_HEIGHT = 100;
    protected static final double SPACING = 20;

    private static StackPane gameMenuRoot = new StackPane();
    private static GameMenu currentMenu = AccountMenu.getAccountMenu();

    public static GameMenu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(GameMenu currentMenu) {
        GameMenu.currentMenu = currentMenu;
    }

    public void setState(String input) {
        System.out.println("you're stucked in gameMenu");
    }

    private void showMenu() {

    }

    public  Scene getMenuScene(){
        return null;
    }

    public StackPane getMenuRoot(){
        return null;
    }

    public static void addToGameMenuRoot(Node node){
        gameMenuRoot.getChildren().add(node);
    }

    public static StackPane getGameMenuRoot(){
        return gameMenuRoot;
    }

}

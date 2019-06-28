package GamePackage;

import javafx.scene.Group;

public abstract class GameMenu extends Group {
    public static final double MENU_WIDTH = 400;
    public static final double MENU_HEIGHT = 500;

    abstract boolean execCommand(String input);

    abstract void showMenu();
}

package GamePackage;

import javafx.scene.Group;

public abstract class GameMenu extends Group {
    public static final double MENU_WIDTH = 400;
    public static final double MENU_HEIGHT = 500;
    ClientInfo client;

    GameMenu(ClientInfo client){
        this.client=client;
    }

    void setCurrentMenu(){
        client.setCurrentMenu(this);
    }

    abstract boolean execCommand(String input) throws Exception;

    abstract void showMenu();
}

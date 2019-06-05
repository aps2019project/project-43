package GamePackage;

public class CollectionMenu extends GameMenu {

    private static GameMenu collectionMenu = new CollectionMenu();

    private CollectionMenu(){

    }

    public static GameMenu getCollectionMenu(){
        return collectionMenu;
    }

    @Override
    public void setState(String input) {

    }

    private void showMenu(){

    }

    public static void goToCollectionMenu(){
        GameMenu.setCurrentMenu(CollectionMenu.getCollectionMenu());
        ((CollectionMenu) collectionMenu).showMenu();
    }
}


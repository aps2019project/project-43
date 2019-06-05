package GamePackage;

public class BattleMenu extends GameMenu {

    private static GameMenu battleMenu = new BattleMenu();

    private BattleMenu(){

    }

    public static GameMenu getBattleMenu(){
        return battleMenu;
    }

    @Override
    public void setState(String input) {
        switch (input){
            case "exit":{
                MainMenu.goToMainMenu();
                break;
            }
        }
    }

    private void showMenu(){

    }

    public static void goToBattleMenu(){
        GameMenu.setCurrentMenu(BattleMenu.getBattleMenu());
        ((BattleMenu) battleMenu).showMenu();
    }
}

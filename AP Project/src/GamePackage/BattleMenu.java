package GamePackage;

import java.util.Scanner;

public class BattleMenu extends GameMenu {

    private static GameMenu battleMenu = new BattleMenu();

    private BattleMenu(){

    }

    public static GameMenu getBattleMenu(){
        return battleMenu;
    }

    @Override
    public void setState(String input) {
        boolean valid = false;
        input = input.trim().toLowerCase();
        while (!valid) {
            switch (input){
                case "single player":
                    //To_Do
                    valid = true;
                    break;
                case "multi player":
                    Battle.setMultiPlayer(true);
                    valid = true;
                    showUsers();
                    break;
                case "exit":
                    valid = true;
                    MainMenu.goToMainMenu();
                    break;
            }
        }
    }

    private void showUsers() {
        boolean valid = false;
        AccountMenu.printUsers();
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        Account rival = AccountMenu.getSingleUser(username);
        while (rival==null || rival.getUsername().equals(AccountMenu.getLoggedAccount().getUsername())){
            System.out.println("please choose a valid username!");
            AccountMenu.printUsers();
            username = scanner.nextLine();
            rival = AccountMenu.getSingleUser(username);
        }
        Battle.setSecondPlayer(rival);

        while (!valid) {
            showMenuBattleMode();
            String modeSelection = scanner.nextLine();
            modeSelection = modeSelection.trim();
            modeSelection = modeSelection.toLowerCase();
            switch (modeSelection) {
                case "hero mode":
                    System.out.println("hero mode has selected");
                    Battle.setMode("hero");
                    Battle.startBattle();
                    valid = true;
                    break;
                case "flag6":

                    valid = true;
                    break;
                case "flag1/2":

                    valid = true;
                    break;
                case "back":
                    valid = true;
                    goToBattleMenu();
                    break;
                case "exit":
                    valid = true;
                    MainMenu.goToMainMenu();
                    break;
            }
        }
    }

    private void showMenuBattleMode() {
        System.out.println("*****Select Battle Mode*****\n" +
                "1. Hero mode\n" +
                "2. Flag6\n" +
                "3. Flag1/2\n" +
                "4. Back\n");
    }

    private void showMenu() {
        System.out.print("*****Battle Menu*****\n" +
                "1. Single player\n" +
                "2. Multi player\n");
    }

    public static void goToBattleMenu(){
        GameMenu.setCurrentMenu(BattleMenu.getBattleMenu());
        ((BattleMenu) battleMenu).showMenu();
    }
}

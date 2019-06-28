package GamePackage;

import java.util.Scanner;

public class BattleMenu extends GameMenu {

    private static GameMenu battleMenu = new BattleMenu();

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
            switch (input){
                case "single player":
                    //todo
                    break;
                case "multi player":
                    showUsers();
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

    private void showUsers() {
        Account.printUsers();
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        Account rival = Account.getUser(username);
        while (rival==null || rival.getUsername().equals(Account.getLoggedAccount().getUsername())){
            System.out.println("please choose a valid username! (exit to go back)");
            Account.printUsers();
            if(username.equalsIgnoreCase("exit")){
                showMenu();
                return;
            }
            username = scanner.nextLine();
            rival = Account.getUser(username);
        }
        showMenuBattleMode(rival);
    }

    private void showMenuBattleMode(Account opponent) {
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);
        while (!valid) {
            System.out.println("*****Select Battle Mode*****");
            System.out.println("1. Hero mode");
            System.out.println("2. Flag6");
            System.out.println("3. Flag1/2");
            System.out.println("4. Back");
            String modeSelection = scanner.nextLine().trim().toLowerCase();
            switch (modeSelection) {
                case "hero mode":
                    System.out.println("hero mode has been selected");
                    if(opponent != null) Main.setCurrentMenu(new PlayMenu(new Battle("hero", Account.getLoggedAccount(), opponent)));
                    else Main.setCurrentMenu(new PlayMenu(new Battle("hero")));
                    valid = true;
                    break;
                case "flag6":

                    valid = true;
                    break;
                case "flag1/2":

                    valid = true;
                    break;
                case "exit":
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }

    void showMenu() {
        System.out.println("*****Battle Menu*****");
        System.out.println("1. Single player");
        System.out.println("2. Multi player");
        System.out.println("3. Help (show menu)");
        System.out.println("2. Exit");
    }

    public static void goToBattleMenu(){
        Main.setCurrentMenu(battleMenu);
    }
}

package GamePackage;

import static GamePackage.Account.*;

public class AccountMenu extends GameMenu {

    private static AccountMenu accountMenu = new AccountMenu();

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        if (getLoggedAccount() == null) {
            switch (input) {
                case "create account":
                    createAccount();
                    break;
                case "login":
                    login();
                    break;
                case "help":
                    showMenu();
                    break;
                case "exit":
                    return false;
                default:
                    System.out.println("Invalid Command");
            }
        } else {
            switch (input) {
                case "main menu":
                    MainMenu.goToMainMenu();
                    break;
                case "show leaderboard":
                    showLeaderboard();
                    break;
                case "save":
                    //todo
                    break;
                case "logout":
                    logout();
                    break;
                case "help":
                    showMenu();
                    break;
                case "exit":
                    return false;
                default:
                    System.out.println("Invalid Command");
            }
        }
        return true;
    }

    void showMenu() {
        if (getLoggedAccount() == null) {
            System.out.println("1. Create account");
            System.out.println("2. Login");
            System.out.println("3. Help (show menu)");
            System.out.println("4. Exit");
        } else {
            System.out.println("1. Main menu");
            System.out.println("2. Show leaderboard");
            System.out.println("3. Save");
            System.out.println("4. Logout");
            System.out.println("5. Help (show menu)");
            System.out.println("6. Exit");
        }
    }

    public static void goToAccountMenu(){
        Main.setCurrentMenu(accountMenu);
    }


}

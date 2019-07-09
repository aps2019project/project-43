package GamePackage;

import static GamePackage.Account.*;

public class AccountMenu extends GameMenu {

    private static AccountMenu accountMenu = new AccountMenu();
    private State state = State.INITIAL;
    private String username;

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        if (getLoggedAccount() == null) {
            switch (state){
                case INITIAL:
                    switch (input) {
                        case "create account":
                            System.out.println("Please Enter UserName");
                            state=State.USERNAME_C;
                            break;
                        case "login":
                            System.out.println("please enter Your Username");
                            state=State.USERNAME_L;
                            break;
                        case "help":
                            showMenu();
                            break;
                        case "exit":
                            return false;
                        default:
                            System.out.println("Invalid Command");
                    }
                    break;
                case USERNAME_C:
                    username=input;
                    System.out.println("And Your Password");
                    state=State.PASSWORD_C;
                    break;
                case PASSWORD_C:
                    createAccount(username, input);
                    state=State.INITIAL;
                    break;
                case USERNAME_L:
                    username=input;
                    System.out.println("Please enter Your password");
                    state=State.PASSWORD_L;
                    break;
                case PASSWORD_L:
                    login(username, input);
                    state=State.INITIAL;
                    break;
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

    public static GameMenu goToAccountMenu(){
        Main.setCurrentMenu(accountMenu);
        return accountMenu;
    }


    private enum State{
        INITIAL,
        USERNAME_C,
        PASSWORD_C,
        USERNAME_L,
        PASSWORD_L
    }
}

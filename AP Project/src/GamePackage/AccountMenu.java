package GamePackage;

import java.util.HashMap;
import java.util.Scanner;

public class AccountMenu extends GameMenu {
    private static GameMenu accountMenu = new AccountMenu();

    private Account loggedAccount;
    private HashMap<String, Account> userMap = new HashMap<>();

    private AccountMenu() {

        createAccountMenu();
    }

    public static GameMenu getAccountMenu() {
        return accountMenu;
    }

    @Override
    public void setState(String input) {
        input = input.trim();
        if (loggedAccount == null) {
            switch (input) {
                case "create account": {
                    createAccount();
                    break;
                }
                case "login": {
                    login();
                    break;
                }
                case "help": {
                    showMenu();
                    break;
                }
                default: {
                    System.out.println("Invalid Command");
                }
            }
        } else {
            switch (input) {
                case "main menu": {
                    MainMenu.goToMainMenu();
                    break;
                }
                case "show leaderboard": {
                    showLeaderboard();
                    break;
                }
                case "save": {

                    break;
                }
                case "logout": {
                    logout();
                    break;
                }
                case "help": {
                    showMenu();
                    break;
                }
                default: {
                    System.out.println("Invalid Command");
                }
            }
        }
    }

    private void showMenu() {
        if (loggedAccount == null) {
            System.out.print("1. Create account\n" +
                             "2. Login\n" +
                             "3. Help\n");
        } else {
            System.out.print("1. Main menu\n" +
                             "2. Show leaderboard\n" +
                             "3. Save\n" +
                             "4. Logout\n" +
                             "5. Help\n");
        }
    }

    private void createAccount() {
        Scanner in = new Scanner(System.in);
        String userName = "";
        String password;
        boolean usernamePicked = false;
        while (!usernamePicked) {
            System.out.println("Please Enter UserName");
            userName = in.nextLine();
            if (userMap.containsKey(userName)) {
                System.out.println("This Username Already Exists");
            } else {
                usernamePicked = true;
            }
        }
        System.out.println("And Your Password");
        password = in.nextLine();
        Account newAccount = new Account(userName, password);
        Account.addAccount(newAccount);
        userMap.put(userName, newAccount);
        loggedAccount = newAccount;
        System.out.println("Your account created successfully ");
        showMenu();
    }

    private void login() {
        Scanner in = new Scanner(System.in);
        System.out.println("please enter Your Username");
        String username = in.nextLine();
        if (userMap.containsKey(username)) {
            System.out.println("Please enter Your password");
            String password = in.nextLine();
            if (userMap.get(username).getPassword().equals(password)) {
                loggedAccount = userMap.get(username);
                System.out.println("You're logged in");
            } else {
                System.out.println("Incorrect Password");
            }
        } else {
            System.out.println("this username doesn't exist");
        }
    }

    private void showLeaderboard() {
        System.out.println(new Leaderboard(Account.getAccounts()).sortByWins());
    }

    private void logout() {
        if (loggedAccount != null) {
            loggedAccount = null;
            System.out.println("You're successfully logged out");
        } else
            System.out.println("You're not even logged in");
    }

    public Account getLoggedAccount(){
        return loggedAccount;
    }

    public static void goToAccountMenu(){
        GameMenu.setCurrentMenu(AccountMenu.getAccountMenu());
        ((AccountMenu)accountMenu).showMenu();
    }

    private void createAccountMenu(){


    }

}

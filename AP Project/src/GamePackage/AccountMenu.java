package GamePackage;

import java.util.HashMap;
import java.util.Scanner;

public class AccountMenu extends GameMenu {

    private static Account loggedAccount;
    private static HashMap<String, Account> userMap = new HashMap<>();
    private static GameMenu accountMenu = new AccountMenu();

    private AccountMenu(){
        makeFakeAccounts();
    }

    public static GameMenu getAccountMenu() {
        return accountMenu;
    }

    public static Account getSingleUser(String name) {
        return userMap.get(name);
    }

    public static void printUsers() {
        int count = 0;
        System.out.println("*****Users List*****");
        for (String key: userMap.keySet()) {
            System.out.println(count + ". " + key);
            count++;
        }
    }

    static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    public void setState(String input) {
        input = input.trim().toLowerCase();
        if (getLoggedAccount() == null) {
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

    static void showMenu() {
        if (getLoggedAccount() == null) {
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

    static void createAccount() {
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
        Battle.setFirstPlayer(newAccount);
        loggedAccount = newAccount;
        System.out.println("Your account created successfully ");
        showMenu();
    }

    static void login() {
        Scanner in = new Scanner(System.in);
        System.out.println("please enter Your Username");
        String username = in.nextLine();
        if (userMap.containsKey(username)) {
            System.out.println("Please enter Your password");
            String password = in.nextLine();
            if (userMap.get(username).getPassword().equals(password)) {
                loggedAccount = userMap.get(username);
                Battle.setFirstPlayer(userMap.get(username));
                System.out.println("You're logged in");
            } else {
                System.out.println("Incorrect Password");
            }
        } else {
            System.out.println("this username doesn't exist");
        }
    }

    public void makeFakeAccounts(){
        createAccount("ali");
        createAccount("shahab");
        createAccount("mammad");
        login("ali");
    }
    private void createAccount(String userName) {
        Account newAccount = new Account(userName, "");
        Account.addAccount(newAccount);
        userMap.put(userName, newAccount);
        System.out.println("Your account created successfully " + userName);
    }
    private void login(String username) {
        loggedAccount = userMap.get(username);
        Battle.setFirstPlayer(userMap.get(username));
        System.out.println("You're logged in " + username);
    }

    private void showLeaderboard() {
        System.out.println(new Leaderboard(Account.getAccounts()).sortByWins());
    }

    static void logout() {
        if (loggedAccount != null) {
            loggedAccount = null;
            System.out.println("You're successfully logged out");
        } else
            System.out.println("You're not even logged in");
    }

    public static void goToAccountMenu(){
        GameMenu.setCurrentMenu(AccountMenu.getAccountMenu());
        showMenu();
    }


}

package GamePackage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private ArrayList<Account> accounts = new ArrayList<>();
    private HashMap<String, Account> userMap = new HashMap<>();
    private Account loggedAccount;
    private Game activeGame;
    private static World world = new World();

    private World() {
    }

    public static World getInstance() {
        return world;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Game getActiveGame() {
        return activeGame;
    }

    public void AccountMenu() {
        AccountFunctions accountFunctions = AccountFunctions.INVALID;
        while(true) {
            String[] userInput = Main.scan.nextLine().split(" ");
            accountFunctions.setState(userInput);
            accountFunctions.getState().runFunc(userInput);
        }
    }

    public void MainMenu() {
        MainMenuFunctions mainMenuFunctions = MainMenuFunctions.INVALID;
        do {
            String[] userInput = Main.scan.nextLine().split(" ");
            mainMenuFunctions.setState(userInput);
            mainMenuFunctions.getState().enter();
        } while (mainMenuFunctions.getState() != MainMenuFunctions.EXIT);
    }

    public void createAccount(String username) {
        if (userMap.containsKey(username)) {
            System.out.println("this username already exists");
        } else {
            System.out.println("please enter your password:");
            Account account = new Account(username, Main.scan.nextLine());
            accounts.add(account);
            userMap.put(username, account);
            loggedAccount = account;
        }
    }

    public void login(String username) {
        if (userMap.containsKey(username)) {
            System.out.println("please enter your password:");
            Account account = new Account(username, Main.scan.nextLine());
            Account origin = userMap.get(username);
            if (origin.equals(account)) {
                System.out.println("you are logged in");
                loggedAccount = origin;
            } else {
                System.out.println("your password is incorrect");
            }
        }
    }

    public void showLeaderBoard() {
        Leaderboard leaderboard = new Leaderboard(accounts);
        System.out.println(leaderboard.sortByWins());
    }

    public void save() {
    }

    public void logout() {
        if (loggedAccount != null) {
            loggedAccount = null;
            System.out.println("you are successfully logged out");
        } else {
            System.out.println("you are not logged in");
        }
    }

    public void search(String name) {
        loggedAccount.search(name);
    }

    public void show() {
        loggedAccount.getCollection().show();
    }

    public void showDeck(String name) {
    }

    public void showALlDecks() {
    }

    public void createDeck(String name) {
        loggedAccount.createDeck(name);
    }

    public void deleteDeck(String name) {
        loggedAccount.deleteDeck(name);
    }

    public void validateDeck(String name) {
        loggedAccount.getDeck(name).validate();
    }

    public void selectMainDeck(String name) {
        loggedAccount.setMainDeck(name);
    }
}

package GamePackage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private ArrayList<Account> accounts = new ArrayList<>();
    private HashMap<String, Account> userMap = new HashMap<>();
    private Account loggedAccount;
    private Game activeGame;
    //    private String currentMenu = "AccountMenu";
    private static World world = new World();
    private boolean AccountMenu = true;
    private boolean MainMenu = false;
    private boolean ShopMenu = false;
    private boolean CollectionMenu = false;

    private World() {
    }

    public static World getInstance() {
        return world;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account getLoggedAccount() {
        return loggedAccount;
    }

    public Game getActiveGame() {
        return activeGame;
    }

    public void enterShopMenu() {
        this.ShopMenu = true;
        this.MainMenu = false;
    }

    public void enterMainMenu() {
        this.MainMenu = true;
        this.ShopMenu = false;
        this.CollectionMenu = false;
    }

    public void enterCollectionMenu() {
        this.CollectionMenu = true;
        this.MainMenu = false;
    }

    public void MenuController() {
        AccountFunctions accountFunctions = AccountFunctions.INVALID;
        MainMenuFunctions mainMenuFunctions = MainMenuFunctions.INVALID;
        ShopFunctions shopFunctions = ShopFunctions.INVALID;
        while (true) {
            String[] input = Main.scan.nextLine().split(" ");
            if(AccountMenu){
                //todo
            }

        }
    }

    public void AccountMenu() {
        AccountFunctions accountFunctions = AccountFunctions.INVALID;
        while (AccountMenu) {
            String[] userInput = Main.scan.nextLine().toUpperCase().split(" ");
            accountFunctions.setState(userInput);
            accountFunctions.getState().runFunc(userInput);
        }
    }

    public void MainMenu() {
        MainMenuFunctions mainMenuFunctions = MainMenuFunctions.INVALID;
        do {
            String[] userInput = Main.scan.nextLine().toUpperCase().split(" ");
            mainMenuFunctions.setState(userInput);
            mainMenuFunctions.getState().enter();

        } while (MainMenu);
        if (mainMenuFunctions.getState() == MainMenuFunctions.EXIT){
            AccountMenu = true;
            MainMenu = false;
            ShopMenu = false;
        }
    }

    public void ShopMenu() {
        ShopFunctions shopFunctions = ShopFunctions.INVALID;
        do {
            String[] userInput = Main.scan.nextLine().toUpperCase().split(" ");
            shopFunctions.setState(userInput);
            shopFunctions.doSomething(userInput);
        } while (ShopMenu);
        if (shopFunctions.getState() == ShopFunctions.EXIT){
            MainMenu = true;
            ShopMenu = false;
        }
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
            AccountMenu = false;
            MainMenu = true;
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
                AccountMenu = false;
                MainMenu = true;
            } else {
                System.out.println("your password is incorrect");
            }
        } else
            System.out.println("/Nothing Found/"); //todo remove
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

//    public void show() {
//        loggedAccount.getCollection().show();
//    }

    public void showDeck(String name) {
    }

    public void showALlDecks() { loggedAccount.showAllDecks();
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

    public void addToDeck(String objectName, String deckName){

        loggedAccount.addObjectToDeck(objectName, deckName);

    }

    public void removeFromDeck (String objectName, String deckName){
        loggedAccount.removeObjectFromDeck(objectName, deckName);
    }
    public void exit (String menuName){

        if(menuName.equals("collection") || menuName.equals("shop") || menuName.equals("battle") || menuName.equals("exit") || menuName.equals("help")){
            MainMenuFunctions.showMenu();
        }



    }
}
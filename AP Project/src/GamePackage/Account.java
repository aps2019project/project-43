package GamePackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Account {
    private static List<Account> accounts = new ArrayList<>();
    private static Account loggedAccount;
    private static HashMap<String, Account> userMap = new HashMap<>();
    private ArrayList<Match> matches = new ArrayList<>();

    public void addMatch(Match match){
        matches.add(match);
    }

    static void showLeaderboard() {
        System.out.println(new Leaderboard(accounts).sortByWins());
    }

    public static Account getUser(String name) {
        return userMap.get(name);
    }

    public static void printUsers() {
        int count = 1;
        System.out.println("*****Users List*****");
        for (String key: userMap.keySet()) {
            System.out.println(count++ + ". " + key);
        }
    }

    static Account getLoggedAccount(){
        return loggedAccount;
    }

    private String username;
    private String password;
    private int money = 15000;
    private Collection collection = new Collection();
    private ArrayList<Deck> decks = new ArrayList<>();
    private HashMap<String, Deck> deckHashMap = new HashMap<>();
    private Deck mainDeck;
    private int wins = 0;
    private Shop shop = new Shop();

    Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public int getMoney() {
        return money;
    }

    public void win(int amount) {
        money+=amount;
        wins++;
    }

    public void pay(int amount){
        money-=amount;
    }

    public void getPaid(int amount){
        money+=amount;
    }

    public Collection getCollection() {
        return collection;
    }

    public Shop getShop(){
        return shop;
    }

    private Deck getDeck(String name) {
        return deckHashMap.get(name);
    }

    public void validateDeck(String deckName){
        Deck deck = getDeck(deckName);
        if(deck==null){
            System.out.println("there's no such deck!");
            return;
        }
        deck.validate();
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(String name) {
        Deck deck = getDeck(name);
        if(deck==null){
            System.out.println("there's no such deck!");
            return;
        }
        this.mainDeck = deck;
    }

    public int getWins() {
        return wins;
    }

    public void showDeck(String name) {
        Deck deck = getDeck(name);
        if(deck==null){
            System.out.println("there's no such deck!");
            return;
        }
        deck.showDeck();
    }

    public void showAllDecks(){
        int counter = 1;
        if(this.mainDeck != null){
            System.out.println(counter++ + " : " + mainDeck.getName() + " :");
            this.mainDeck.showDeck();
        }
        for (Deck deck : this.decks) {
            if (deck != mainDeck) {
                System.out.println(counter++ + " : " + deck.getName() + " :");
                deck.showDeck();
            }
        }
    }

    public void createDeck(String name) {
        Deck deck;
        if (getDeck(name) == null) {
            deck = new Deck(name);
            decks.add(deck);
            deckHashMap.put(name, decks.get(decks.size()-1));
        } else {
            System.out.println("a deck with this name already exists");
        }
    }

    public void deleteDeck(String name) {
        if (getDeck(name) != null) {
            decks.remove(getDeck(name));
            deckHashMap.remove(name);
        } else {
            System.out.println("there is no such deck!");
        }
    }

    public void addObjectToDeck (String objectName, String deckName){
        Deck deck = this.getDeck(deckName);
        if(deck==null){
            System.out.println("there is no such deck!");
            return;
        }
        List<GameObject> objects = collection.getCards(objectName);
        if(objects.size() == 0){
            System.out.println("there's no such card/item in the collection!");
            return;
        }
        GameObject object = null;
        for(GameObject obj: objects){
            if(!deck.hasCard(obj.getId()))object = obj;
        }
        if(object==null){
            System.out.println("Object exists in the deck");
        }

        if (object instanceof Hero) {
            if (deck.getHero() != null) {
                System.out.println("there's a hero in the deck!");
            } else {
                deck.setHero((Hero) object);
            }
        } else if (object instanceof Item) {
            if (deck.getItem() != null) {
                System.out.println("there's an item in the deck!");
            } else {
                deck.setItem((Item) object);
            }

        } else {
            if (object==null) {
                System.out.println("the card exists in the deck!");
            } else {
                deck.addCard((Card) object);
            }
        }

    }

    public void removeObjectFromDeck (String objectName, String deckName){
        Deck deck = this.getDeck(deckName);
        if(deck==null){
            System.out.println("there is no such deck!");
            return;
        }

        Card hero = deck.getHero();
        Card card = deck.getCard(objectName);
        Item item = deck.getItem();

        if (item != null) {
            this.getDeck(deckName).removeItem();
        } else if (hero != null) {
            this.getDeck(deckName).removeHero();
        } else if (card != null) {
            deck.removeCard(card);
        } else {
            System.out.println("there is no such card!");
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
        accounts.add(newAccount);
        userMap.put(userName, newAccount);
        loggedAccount = newAccount;
        System.out.println("Your account created successfully ");
        AccountMenu.goToAccountMenu();
    }

    static void login() {
        Scanner in = new Scanner(System.in);
        System.out.println("please enter Your Username");
        String username = in.nextLine();
        if (userMap.containsKey(username)) {
            System.out.println("Please enter Your password");
            String password = in.nextLine();
            if (userMap.get(username).password.equals(password)) {
                loggedAccount = userMap.get(username);
                System.out.println("You're logged in");
            } else {
                System.out.println("Incorrect Password");
            }
        } else {
            System.out.println("this username doesn't exist");
        }
        AccountMenu.goToAccountMenu();
    }

    public static void makeFakeAccounts(){
        createAccount("ali");
        createAccount("shahab");
        createAccount("mammad");
        loggedAccount = userMap.get("ali");
        System.out.println("You're logged in ali");
    }

    private static void createAccount(String userName) {
        Account newAccount = new Account(userName, "");
        accounts.add(newAccount);
        userMap.put(userName, newAccount);
        System.out.println("Your account created successfully " + userName);
    }


    static void logout() {
        if (loggedAccount != null) {
            loggedAccount = null;
            System.out.println("You're successfully logged out");
        } else
            System.out.println("You're not even logged in");
    }

    @Override
    public String toString() {
        return "UserName: " + username + " - Wins: " + wins;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Account && this.getUsername().equals(((Account) o).getUsername());
    }
}
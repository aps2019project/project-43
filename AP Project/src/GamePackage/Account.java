package GamePackage;

import java.util.ArrayList;
import java.util.HashMap;

public class Account {
    private String username;
    private String password;
    private String status;
    private int money = 15000;
    private Collection collection = new Collection();
    private ArrayList<Match> matches = new ArrayList<>();
    private Match currentMatch;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck;
    private HashMap<String, Deck> deckHashMap = new HashMap<>();
    private int wins = 0;
    private Shop shop = new Shop();

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public int getMoney() {
        return money;
    }

    public Collection getCollection() {
        return collection;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public Deck getDeck(String name) {
        return deckHashMap.get(name);
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(String name) {
        this.mainDeck = getDeck(name);
    }

    public int getWins() {
        return wins;
    }

    public void showDeck(String name) {
        Deck deck = getDeck(name);
    }

    public void createDeck(String name) {
        if (getDeck(name) == null) {
            decks.add(new Deck(name));
        } else {
            System.out.println("a deck with this name already exists");
        }
    }

    public void deleteDeck(String name) {
        if (getDeck(name) == null) {
            decks.remove(getDeck(name));
        } else {
            System.out.println("deck doesn't exists");
        }
    }

    public void search(String name) {
        boolean found = false;
        for (Card card : collection.getCards()) {
            if (card.getName().equals(name)) {
                System.out.println(card.getCardID());
                found = true;
            }
        }
        if (!found) {
            System.out.println(name + " doesn't exist");
        }
    }

    @Override
    public String toString() {
        return "UserName: " + username + " - Wins: " + wins;
    }

    public boolean equals(Account account) {
        return this.username.equals(account.getUsername())
                && this.password.equals(account.getPassword());
    }
}

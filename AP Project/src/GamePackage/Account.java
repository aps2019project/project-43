package GamePackage;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String status;
    private int money = 15000;
    private Collection collection = new Collection();
    private ArrayList<Match> matches = new ArrayList<>();
    private Match currentMatch;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck = new Deck();

    {

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

    public Deck getMainDeck() {
        return mainDeck;
    }

    public boolean equals(String username) {
        if(this.username.equals(username)) {
            System.out.println("this username already exist");
            return false;
        } else {
            return true;
        }
    }
}

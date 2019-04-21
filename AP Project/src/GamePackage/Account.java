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
    private int wins = 0;

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

    public Deck getMainDeck() {
        return mainDeck;
    }

    public int getWins() {
        return wins;
    }

    public boolean equals(Account account) {
        if (this.username.equals(account.getUsername())
                && this.password.equals(account.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "UserName: " + username + " - Wins: " + wins;
    }
}

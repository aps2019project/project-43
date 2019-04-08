package GamePackage;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String status;
    private int money = 15000;
    private Collection collection = new Collection();
    private ArrayList<Match> matches = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck = new Deck();
    private Graveyard graveyard;
    private Match currentMatch;
}

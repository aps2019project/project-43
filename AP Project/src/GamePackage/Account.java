package GamePackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account {
    private String username;
    private String password;
    private int money = 15000;
    private Collection collection = new Collection();
    private ArrayList<Deck> decks = new ArrayList<>();
    private HashMap<String, Deck> deckHashMap = new HashMap<>();
    private Deck mainDeck;
    private int wins = 0;
    private String authToken;
    private ArrayList<Match> matches = new ArrayList<>();
    private ClientInfo client;
    private int tokenCnt=0;

    public void updateToken(){
        authToken += tokenCnt++;
    }

    public void setClient(ClientInfo client) {
        this.client = client;
    }

    public ClientInfo getClient() {
        return client;
    }

    public void addMatch(Match match){
        matches.add(match);
    }

    Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.authToken = username+"Token";
    }

    public String getAuthToken() {
        return authToken;
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

    private Deck getDeck(String name) {
        return deckHashMap.get(name);
    }

    public void validateDeck(String deckName){
        Deck deck = getDeck(deckName);
        if(deck==null){
            client.sendPrint("there's no such deck!");
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
            client.sendPrint("there's no such deck!");
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
            client.sendPrint("there's no such deck!");
            return;
        }
        client.sendPrint(deck.showDeck());
    }

    public void showAllDecks(){
        int counter = 1;
        if(this.mainDeck != null){
            client.sendPrint(counter++ + " : " + mainDeck.getName() + " :");
            client.sendPrint(this.mainDeck.showDeck());
        }
        for (Deck deck : this.decks) {
            if (deck != mainDeck) {
                client.sendPrint(counter++ + " : " + deck.getName() + " :");
                client.sendPrint(deck.showDeck());
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
            client.sendPrint("a deck with this name already exists");
        }
    }

    public void deleteDeck(String name) {
        if (getDeck(name) != null) {
            decks.remove(getDeck(name));
            deckHashMap.remove(name);
        } else {
            client.sendPrint("there is no such deck!");
        }
    }

    public void addObjectToDeck (String objectName, String deckName){
        Deck deck = this.getDeck(deckName);
        if(deck==null){
            client.sendPrint("there is no such deck! "+deckName);
            return;
        }
        List<GameObject> objects = collection.getCards(objectName);
        if(objects.size() == 0){
            client.sendPrint("there's no such card/item in the collection! "+objectName);
            return;
        }
        GameObject object = null;
        for(GameObject obj: objects){
            if(!deck.hasCard(obj.getId()))object = obj;
        }
        if(object==null){
            client.sendPrint("Object exists in the deck " + objectName);
        }

        if (object instanceof Hero) {
            if (deck.getHero() != null) {
                client.sendPrint("there's a hero in the deck!");
            } else {
                deck.setHero((Hero) object);
            }
        } else if (object instanceof Item) {
            if (deck.getItem() != null) {
                client.sendPrint("there's an item in the deck!");
            } else {
                deck.setItem((Item) object);
            }

        } else {
            if (object==null) {
                client.sendPrint("the card exists in the deck!");
            } else {
                deck.addCard(client, (Card) object);
            }
        }

    }

    public void removeObjectFromDeck (String objectName, String deckName){
        Deck deck = this.getDeck(deckName);
        if(deck==null){
            client.sendPrint("there is no such deck!");
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
            client.sendPrint("there is no such card!");
        }
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public boolean checkToken(String authToken){
        return this.authToken.equals(authToken);
    }

    @Override
    public String toString() {
        return "UserName: " + username + " - Wins: " + wins;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Account && this.getUsername().equals(((Account) o).getUsername());
    }

    public void doYourTurn(Battle battle) { ;
    }

}
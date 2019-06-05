package GamePackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Account {
    private static List<Account> accounts = new ArrayList<>();

    public static List<Account> getAccounts() {
        return accounts;
    }

    public static void addAccount(Account account){
        accounts.add(account);
    }

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

    public Shop getShop(){
        return shop;
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
        if(getDeck(name) != null) {
            getDeck(name).show();
        }
        else{
            System.out.println("there is no such deck!");
        }
    }

    public void showAllDecks(){
        int counter = 0;
        if(this.mainDeck != null){
            counter++;
            System.out.println(counter + " : " + "deck_" + counter + " :");
            this.mainDeck.show();
        }
        for (Deck deck : this.decks){
            if(deck.getName() != mainDeck.getName()){
                counter++;
                System.out.println(counter + " : " + "deck_" + counter + " :");
                deck.show();
            }
        }

    }

    public void createDeck(String name) {
        if (getDeck(name) == null) {
            decks.add(new Deck(name));
        } else {
            System.out.println("a deck with this name already exists");
        }
    }

    public void deleteDeck(String name) {
        if (getDeck(name) != null) {
            decks.remove(getDeck(name));
        } else {
            System.out.println("there is no such deck!");
        }
    }

    public void selectDeck (String name){
        if (getDeck(name) != null){
            this.mainDeck = getDeck(name);
        }
        else{
            System.out.println("there's no such deck!");
        }
    }



    public Card findCardInDeck (String cardName, String deckName){

        Deck deck = this.getDeck(deckName);

        for (Card card : deck.getCards()){
            if(card.getName().equals(cardName)){
                return card;
            }
        }
        return null;
    }


    public Card findCardInCollection (String cardName){

        Collection collection = this.getCollection();

        for (Card card : collection.getCards()){
            if(card.getName().equals(cardName)){
                return card;
            }
        }
        return null;
    }

    public Hero findHeroInCollection (String heroName){
        Collection collection = this.getCollection();

        for (Card hero : collection.getHeroes()){
            if(hero.getName().equals(heroName)){
                return (Hero) hero;
            }
        }
        return null;
    }


    public Item findItemInCollection (String itemName){

        Collection collection = this.getCollection();

        for (Item item : collection.getItems()){
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    public void search(String name) {
        if (this.findItemInCollection(name) != null){
            System.out.println("the item exists in the collection:" + this.findItemInCollection(name).getItemID());
        }
        else if (this.findCardInCollection(name) != null){
            System.out.println("the card exists in the collection:" + this.findCardInCollection(name).getCardID());
        }
        else if (this.findHeroInCollection(name) != null){
            System.out.println("the card exists in the collection:" + this.findHeroInCollection(name).getCardID());
        }
        else{
            System.out.println("the card/item doesn't exist in the collection!");
        }
    }

    public void addObjectToDeck (String objectName, String deckName){

        Deck deck = this.getDeck(deckName);
        Card cardInCollection = this.findCardInCollection(objectName);
        Item itemInCollection = this.findItemInCollection(objectName);
        Hero heroInCollection = this.findHeroInCollection(objectName);

        if(deck != null) {


            if (heroInCollection != null) {

                if (deck.getHero() != null) {
                    System.out.println("there's a hero in the deck!");
                } else {
                    deck.setHero(this.findHeroInCollection(objectName));
                }
            } else if (cardInCollection != null) {

                if (deck.getSize() < deck.getMaxSize()) {//todo debug
                    if (findCardInDeck(objectName, deckName).getName().equals(objectName)) {
                        System.out.println("the card exists in the deck!");
                    } else {
                        deck.setCard(this.findCardInCollection(objectName));
                    }
                } else {
                    System.out.println("there's no free space!");
                }

            } else if (itemInCollection != null) {

                if (deck.getItem() != null) {
                    System.out.println("there's an item in the deck!");
                } else {
                    deck.setItem(this.findItemInCollection(objectName));
                }

            } else {
                System.out.println("there's no such card/item in the collection!");
            }
        }
        else{
            System.out.println("there is no such deck!");
        }

    }

    public void removeObjectFromDeck (String objectName, String deckName){

        Deck deck = this.getDeck(deckName);
        Card hero = deck.getHero();
        Card card = this.findCardInDeck(objectName, deckName);
        Item item = deck.getItem();

        if( deck != null) {

            if (item != null) {
                this.getDeck(deckName).removeItem();
            } else if (hero != null) {
                this.getDeck(deckName).removeHero();
            } else if (card != null) {
                this.getDeck(deckName).removeCard(this.findCardInDeck(objectName, deckName));
            }
        }
        else{
            System.out.println("there is no such deck!");
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

    public void MoneyTransaction(int X){
        this.money = money + X;
    }

    public void addCard(){

    }
}
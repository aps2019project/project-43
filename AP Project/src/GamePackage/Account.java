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
    private int money = 15000;
    private Collection collection = new Collection();
    private ArrayList<Match> matches = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private HashMap<String, Deck> deckHashMap = new HashMap<>();
    private Deck mainDeck = createDeck("maindeck");
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

    public int getMoney() {
        return money;
    }
    public void addMoney(int amount) {
        money+=amount;
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
    public void addWins() {
        wins++;
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
            System.out.println(counter + " : " + mainDeck.getName() + " :");
            this.mainDeck.show();
        }
        for (Deck deck : this.decks) {
            if (deck.getName() != mainDeck.getName()) {
                counter++;
                System.out.println(counter + " : " + deck.getName() + " :");
                deck.show();
            }
        }
    }

    public Deck createDeck(String name) {
        Deck deck = null;
        if (getDeck(name) == null) {
            deck = new Deck(name);
            decks.add(deck);
            deckHashMap.put(name, decks.get(decks.size()-1));
        } else {
            System.out.println("a deck with this name already exists");
        }
        return deck;
    }

    public void deleteDeck(String name) {
        if (getDeck(name) != null) {
            decks.remove(getDeck(name));
            deckHashMap.remove(name);
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
            if(card.getName().equalsIgnoreCase(cardName)){
                return card;
            }
        }
        return null;
    }


    public boolean findCardInDeck (int cardName, String deckName){

        Deck deck = this.getDeck(deckName);

        for (Card card : deck.getCards()){
            if(card.getCardID()==(cardName)){
                return true;
            }
        }
        return false;
    }

    public boolean isCardInDeck (int id, Deck deck){

        for (Card card : deck.getCards()){
            if(card.getCardID()==(id)){
                return true;
            }
        }
        return false;
    }


    public Card findCardInCollection (String cardName){

        Collection collection = this.getCollection();

        for (Card card : collection.getCards()){
            if(card.getName().equalsIgnoreCase(cardName)){
                return card;
            }
        }
        return null;
    }

    public Hero findHeroInCollection (String heroName){
        Collection collection = this.getCollection();

        for (Card hero : collection.getHeroes()){
            if(hero.getName().equalsIgnoreCase(heroName)){
                return (Hero) hero;
            }
        }
        return null;
    }


    public Item findItemInCollection (String itemName){

        Collection collection = this.getCollection();

        for (Item item : collection.getItems()){
            if(item.getName().equalsIgnoreCase(itemName)){
                return item;
            }
        }
        return null;
    }

    public Card findCardInCollection (String cardName, Deck deck){

        Collection collection = this.getCollection();

        for (Card card : collection.getCards()){
            if(card.getName().equalsIgnoreCase(cardName) && !isCardInDeck(card.getCardID(), deck) ){
                return card;
            }
        }
        return null;
    }


    public Card findCardInCollection (int cardName){

        Collection collection = this.getCollection();

        for (Card card : collection.getCards()){
            if(card.getCardID()==cardName){
                return card;
            }
        }
        return null;
    }

    public Hero findHeroInCollection (int heroName){
        Collection collection = this.getCollection();
        System.out.println(collection.getHeroes().size());
        for (Card hero : collection.getHeroes()){
            if(hero.getCardID()==heroName){
                return (Hero) hero;
            }
        }
        return null;
    }


    public Item findItemInCollection (int itemName){

        Collection collection = this.getCollection();

        for (Item item : collection.getItems()){
            if(item.getItemID() ==itemName){
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

    public void addObjectToDeck (String objectId, String deckName){

//        int objectName= parseInt(objectId);
        String objectName=objectId;
        Deck deck = this.getDeck(deckName);
        if(deck==null){
            System.out.println("there is no such deck!");
            return;
        }
        Card cardInCollection = this.findCardInCollection(objectName,deck);
        Item itemInCollection = this.findItemInCollection(objectName);
        Hero heroInCollection = this.findHeroInCollection(objectName);

        if (heroInCollection != null) {

            if (deck.getHero() != null) {
                System.out.println("there's a hero in the deck!");
            } else {
                deck.setHero(heroInCollection);
            }
        } else if (cardInCollection != null) {

            if (deck.getSize() < deck.getMaxSize()) {
                if (findCardInDeck(objectName, deckName)!=null) {
                    System.out.println("the card exists in the deck!");
                } else {
                    deck.addCard(cardInCollection);
                }
            } else {
                System.out.println("there's no free space!");
            }

        } else if (itemInCollection != null) {

            if (deck.getItem() != null) {
                System.out.println("there's an item in the deck!");
            } else {
                deck.setItem(itemInCollection);
            }

        } else {
            System.out.println("there's no such card/item in the collection!");
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

    public void MoneyTransaction(int X){
        this.money = money + X;
    }

    public void addCard(){

    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Account && this.getUsername().equals(((Account) o).getUsername());
    }
}
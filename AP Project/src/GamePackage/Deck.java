package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private String name;
    private Hero hero;
    private Item item;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> cardsNotInHand = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();
    private Card nextCard;//the card that's visible
    private ArrayList<Collectible> items =new ArrayList<>();

    Deck(String name) {
        this.name = name;
    }

    public void addItem(Collectible item){items.add(item);}

    public void setItem(Item item){
        this.item = item;
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }

    public void addCard(Card card){
        if(cards.size()<20) this.cards.add(card);
        else System.out.println("there's no free space!");
    }

    public String getName() {
        return name;
    }

    public Card getNextCard() {
        return nextCard;
    }

    public Item getItem() {
        return item;
    }

    public void startGame(){
        for(Card card: cards){
            if(card instanceof Hero || card instanceof Minion){
                ((Force)card).startGame();
            }
        }
        cardsNotInHand.clear();
        cardsNotInHand.addAll(cards);
        Collections.shuffle(cardsNotInHand);
        nextCard = null;
        items.clear();
        hand.clear();
        updateNextCard();
        for(int i=0;i<5;i++) addNextCard();
    }

    public Hero getHero() {
        return hero;
    }

    private void updateNextCard(){
        if(cardsNotInHand.size()>0) nextCard = cardsNotInHand.get(0);
        else nextCard = null;
    }

    public void addNextCard() {
        while(hand.size() < 5 && nextCard!=null) {
            cardsNotInHand.remove(nextCard);
            hand.add(nextCard);
            updateNextCard();
        }
    }

    public Card getCardFromHand(String name, boolean use){
        for(Card card: hand){
            if(card.getName().equalsIgnoreCase(name)) {
                if(use) hand.remove(card);
                return card;
            }
        }
        for(Collectible card: items){
            if(card.getName().equalsIgnoreCase(name)) {
                if(use) items.remove(card);
                return card;
            }
        }
        return null;
    }

    public ArrayList<Card> cardsToBeInserted(int mana){
        ArrayList<Card> cards =new ArrayList<>();
        for(Card card: hand){
            if(card.getManaCost()<=mana) {
                cards.add(card);
                mana-=card.getManaCost();
            }
        }
        for(Collectible card: items){
            if(card.getManaCost()<=mana) {
                cards.add(card);
                mana-=card.getManaCost();
            }
        }
        return cards;
    }

    public Card getCard(String cardName){
        for (Card card : cards){
            if(card.getName().equalsIgnoreCase(cardName)){
                return card;
            }
        }
        return null;
    }
    public Card getCard(int id){
        for (Card card : cards){
            if(card.getId()==id){
                return card;
            }
        }
        return null;
    }

    public boolean hasCard(int id){
        for (Card card : cards) {
            if(card.getId()==(id)){
                return true;
            }
        }
        return false;
    }

    public void removeHero() {this.hero = null;}
    public void removeCard(Card card) {this.cards.remove(card);}
    public void removeItem() {this.item = null;}

    public boolean validate() {
        return cards.size() == 20 && getHero() != null;
    }

    public void showHand(){
        int cardCounter = 0;
        System.out.println("Item:");
        System.out.println("\t"+item);
        for (Collectible item: items) {
            System.out.println("\t" + item);
        }
        for (Card card: hand) {
            cardCounter++;
            System.out.println("\t"+cardCounter + " : " + card);
        }
        System.out.println("\tNext Card:");
        System.out.println("\t\t"+cardCounter + " : " + nextCard);
        System.out.println();
    }

    public void showDeck(){
        System.out.println("\tHeroes :");
        if(hero!=null) System.out.println("\t\t"+hero);
        System.out.println("\tItem :");
        if(item!=null) System.out.println(item);
        System.out.println("\tCards :");
        int cardCounter = 0;
        for (Card card: cards) {
            cardCounter++;
            System.out.println("\t\t"+cardCounter + " : " + card);
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        return o!=null && o instanceof Deck && ((Deck) o).name.equals(name);
    }

}
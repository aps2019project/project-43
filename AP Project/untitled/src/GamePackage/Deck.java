package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private String name;
    private Card hero;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();
    private Item item;
    private Card nextCard; //the card that's visible
    private final int maxSize = 5;
    private int size;

    public Deck(String name) {
        this.name = name;
    }

    public void setItem(Item item){

        this.item = item;

    }
    public void setHero(Hero hero){

        this.hero = hero;

    }
    public void setCard(Card card){

        this.cards.add(card);
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

    public void addNextCard() {
        if(hand.size() < 5) {
            Collections.shuffle(cards);
            nextCard = cards.get(0);
            cards.remove(nextCard);
            hand.add(nextCard);
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getHero() {
        return hero;
    }

    public int getSize() {
        this.size = cards.size();
        return size;
    }

    public int getMaxSize(){
        return maxSize;
    }

    public void removeHero() {this.hero = null;}
    public void removeCard(Card card) {this.cards.remove(card);}
    public void removeItem() {this.item = null;}


    public void validate() {
        if(cards.size() == 21 && getHero() != null) {
            System.out.println("deck is valid");
        } else {
            System.out.println("deck is not valid");
        }
    }

    public void show(){

    }


}
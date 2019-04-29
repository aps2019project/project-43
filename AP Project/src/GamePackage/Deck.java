package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private String name;
    private Card hero;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();
    private Item item;
    private Card nextCard = new Card(); //the card that's visible
    private final int maxSize = 5;
    private int size;

    public Deck(String name) {
        this.name = name;
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

    public void validate() {
        if(cards.size() == 21 && getHero() != null) {
            System.out.println("deck is valid");
        } else {
            System.out.println("deck is not valid");
        }
    }
}

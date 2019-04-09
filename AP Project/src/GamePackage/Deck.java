package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private String name;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();
    private Card nextCard = new Card(); //the card that's visible
    private final int maxSize = 5;
    private int size;

    public String getName() {
        return name;
    }

    public Card getNextCard() {
        return nextCard;
    }

    public void addNextCard() {
        if(hand.size() < 5) {
            hand.add(nextCard);
            Collections.shuffle(cards);
            nextCard = cards.get(0);
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


}

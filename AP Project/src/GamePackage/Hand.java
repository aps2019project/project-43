package GamePackage;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    private Card nextCard = new Card(); //the card that's visible
    private final int maxSize = 5;
    private int size;
}

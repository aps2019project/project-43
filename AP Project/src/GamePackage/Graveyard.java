package GamePackage;

import java.util.ArrayList;

public class Graveyard {

    private ArrayList<Card> deadCards = new ArrayList<>();

    public ArrayList<Card> getDeadCards() {
        return deadCards;
    }

    public void setDeadCards(ArrayList<Card> deadCards) {
        this.deadCards = deadCards;
    }

}

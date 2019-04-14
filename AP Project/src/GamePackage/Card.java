package GamePackage;

import java.util.ArrayList;
import java.util.UUID;

public class Card {
    private String name;
    private int moneyCost;
    private int manaCost;
    private ArrayList<Buff> effects = new ArrayList<>();
    private UUID cardID;

    public void printStats(){
    }

    public String getName() {
        return name;
    }

    public int getMoneyCost() {
        return moneyCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    public ArrayList<Buff> getEffects() {
        return effects;
    }

    public UUID getCardID() {
        return cardID;
    }

    public Card() {
    }

    public Card(String name, UUID cardID, int moneyCost, int manaCost) {
        this.name = name;
        this.cardID = cardID;
        this.moneyCost = moneyCost;
        this.manaCost = manaCost;
    }
}

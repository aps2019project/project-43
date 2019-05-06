package GamePackage;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Card {
    private String name;
    private int moneyCost;
    private int manaCost;
    private ArrayList<Buff> effects = new ArrayList<>();
    private AtomicInteger cardID = new AtomicInteger();
    private String info;
    private Account owner;

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

    public AtomicInteger getCardID() {
        return cardID;
    }

    public Card() {
    }

    public Card(String name, int moneyCost, int manaCost, Buff buff, String info) {
        this.name = name;
//        this.cardID = cardID;
        this.moneyCost = moneyCost;
        this.manaCost = manaCost;
        this.effects.add(buff);
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoneyCost(int moneyCost) {
        this.moneyCost = moneyCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setCardID() {
        this.cardID.incrementAndGet();
    }

    public Account getOwner() {
        return owner;
    }

    public String getInfo() {
        return info;
    }

    public void addBuff(Buff buff){
        this.effects.add(buff);
    }
}

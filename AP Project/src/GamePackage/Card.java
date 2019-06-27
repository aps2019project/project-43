package GamePackage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({ "whichTurn" })
public class Card implements Generateble{
    private String name;
    private int moneyCost;
    private int manaCost;
    private ArrayList<Buff> effects = new ArrayList<>();
    private int cardID;
    private String info;
    private Account owner;
    public String file;
    public String description;

    private Cell cardLocation;

    public Cell getCardLocation() {
        return cardLocation;
    }

    public void setCardLocation(Cell cardLocation) {
        this.cardLocation = cardLocation;
    }

    @Override
    public String toString(){
        return null;
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

    public int getCardID() {
        return cardID;
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

    public void setCardID(int id) {
        this.cardID = id;
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

    public void setOwner(Account owner){
        this.owner = owner;
    }

    @Override
    public String getFilePath() {
        return file;
    }
}

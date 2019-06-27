package GamePackage;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private String name;
    private Hero hero;
    private ArrayList<Card> cards = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<>();
    private Item item;
    private Card nextCard;//the card that's visible

    public Deck(String name) {
        this.name = name;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }

    public ArrayList<Minion> getMinions() {
        ArrayList<Minion> minions=new ArrayList<>();
        for(Card card: cards){
            if(card instanceof Minion){
                minions.add((Minion) card);
            }
        }
        return minions;
    }

    public ArrayList<Spell> getSpells() {
        ArrayList<Spell> spells=new ArrayList<>();
        for(Card card: cards){
            if(card instanceof Spell){
                spells.add((Spell) card);
            }
        }
        return spells;
    }

    public void addCard(Card card){
        if(cards.size()<20) this.cards.add(card);
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
        Collections.shuffle(cards);
        updateNextCard();
        for(int i=0;i<5;i++) addNextCard();
    }

    private void updateNextCard(){
        if(cards.size()>0) nextCard = cards.get(0);
        else nextCard = null;
    }

    public void addNextCard() {
        if(hand.size() < 5 && nextCard!=null) {
            cards.remove(nextCard);
            hand.add(nextCard);
            updateNextCard();
        }
    }

    public Card getCardFromHand(String name, boolean used){
        for(Card card: hand){
            if(card.getName().equalsIgnoreCase(name)) {
                if(used) hand.remove(card);
                return card;
            }
        }
        return null;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Hero getHero() {
        return hero;
    }

    public int getSize() {
        return cards.size();
    }

    public int getMaxSize(){
        return 20;
    }

    public void removeHero() {this.hero = null;}
    public void removeCard(Card card) {this.cards.remove(card);}
    public void removeItem() {this.item = null;}


    public boolean validate() {
        if(cards.size() == 20 && getHero() != null) {
            System.out.println("deck is valid");
            return true;
        } else {
            System.out.println("deck is not valid");
            return false;
        }
    }

    public void show(){
        int cardCounter = 0;
        System.out.println("\tHeroes :");
        if(hero!=null) System.out.println("\t\t"+hero);
        System.out.println("\tItem :");
        if(item!=null)
        System.out.println(getItem().toString());
        System.out.println("\tCards :");
        for (Card card: cards) {
            cardCounter++;
            System.out.println("\t\t"+cardCounter + " : " + card);
        }
    }


}
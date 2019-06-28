package GamePackage;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    ArrayList<Hero> getHeroes(){
        ArrayList<Hero> heroes = new ArrayList<>();
        for(Card card: cards){
            if(card instanceof Hero){
                heroes.add((Hero) card);
            }
        }
        return heroes;
    }
    private ArrayList<Minion> getMinions(){
        ArrayList<Minion> minions = new ArrayList<>();
        for(Card card: cards){
            if(card instanceof Minion){
                minions.add((Minion) card);
            }
        }
        return minions;
    }
    private ArrayList<Spell> getSpells(){
        ArrayList<Spell> spells = new ArrayList<>();
        for(Card card: cards){
            if(card instanceof Spell){
                spells.add((Spell) card);
            }
        }
        return spells;
    }
    ArrayList<Item> getItems(){
        return items;
    }

    public void addToCollection(GameObject object) {
        if (object instanceof Item) {
            if(items.size()>=3){
                System.out.println("Collection is full of items");
                return;
            }
            items.add((Item) object);
        } else if (object instanceof Card) {
            cards.add((Card) object);
        }
    }

    public void removeFromCollection(GameObject object) {
        if (object instanceof Item) {
            items.remove(object);
        }
        else if (object instanceof Card) {
            cards.remove(object);
        }
    }

    // Show cards and items
    public void print() {
        System.out.println("Heroes : ");
        for (Card card : getHeroes()) {
            System.out.println("\t"+card+ " - Sell Cost : " + card.getPrice());
        }
        System.out.println("Items :");
        for (Item item : items) {
            System.out.println("\t"+item+ " - Sell Cost : " + item.getPrice());
        }
        System.out.println("Cards : ");
        for (Card card : getMinions()) {
            System.out.println("\t"+card+ " - Sell Cost : " + card.getPrice());
        }
        for (Card card : getSpells()) {
            System.out.println("\t"+card+ " - Sell Cost : " + card.getPrice());
        }
    }

    public GameObject getCard(int ID) {
        for (Card card: cards) {
            if (card.getId() == ID) {
                return card;
            }
        }
        for (Item item: items) {
            if (item.getId() == ID) {
                return item;
            }
        }
        return null;
    }

    public List<GameObject> getCards(String name) {
        List<GameObject> list = new ArrayList<>();
        for (Card card: cards) {
            if (card.getName().equalsIgnoreCase(name)){
                list.add(card);
            }
        }
        for (Item item: items) {
            if (item.getName().equalsIgnoreCase(name)){
                list.add(item);
            }
        }
        return list;
    }


    public void search(String name) {
        ArrayList<GameObject> objs = new ArrayList<>();
        for(Card card: cards){
            if(card.getName().equalsIgnoreCase(name)){
                objs.add(card);
            }
        }
        for(Item item: items){
            if(item.getName().equalsIgnoreCase(name)){
                objs.add(item);
            }
        }
        if(objs.size()>0){
            System.out.println("these are found objects:");
            for(GameObject gameObject: objs){
                System.out.println("\tID: "+gameObject.getId());
            }
        } else{
            System.out.println("the card/item doesn't exist in the collection!");
        }
    }

}

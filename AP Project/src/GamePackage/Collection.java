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

    public void addToCollection(ClientInfo client, GameObject object) {
        if (object instanceof Item) {
            if(items.size()>=3){
                client.sendPrint("Collection is full of items");
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
    public String show() {
        StringBuilder res=new StringBuilder();
        res.append("Heroes : \n");
        for (Card card : getHeroes()) {
            res.append("\t").append(card).append(" - Sell Cost : ").append(card.getPrice()).append("\n");
        }
        res.append("Items :\n");
        for (Item item : items) {
            res.append("\t").append(item).append(" - Sell Cost : ").append(item.getPrice()).append("\n");
        }
        res.append("Cards :\n");
        for (Card card : getMinions()) {
            res.append("\t").append(card).append(" - Sell Cost : ").append(card.getPrice()).append("\n");
        }
        for (Card card : getSpells()) {
            res.append("\t").append(card).append(" - Sell Cost : ").append(card.getPrice()).append("\n");
        }
        return res.toString();
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


    public String search(String name) {
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
            StringBuilder res=new StringBuilder("these are found objects:\n");
            for(GameObject gameObject: objs){
                res.append("\tID: ").append(gameObject.getId()).append("\n");
            }
            return res.toString();
        } else{
            return ("the card/item doesn't exist in the collection!");
        }
    }

}

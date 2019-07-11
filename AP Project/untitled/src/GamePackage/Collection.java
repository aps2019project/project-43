package GamePackage;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    private ArrayList<Card> heroes = new ArrayList<>();
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private final int maxItems = 3;

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Card> getHeroes() {
        return heroes;
    }

    public void addToCollection(Object object) {
        if (object instanceof Hero) {
            heroes.add((Card) object);
        } else if (object instanceof Item) {
            items.add((Item) object);
        } else {
            cards.add((Card) object);
        }
    }

    public void removeFromCollection(Object object) {
        if (object instanceof Hero) {
            heroes.remove(object);
        } else if (object instanceof Item) {
            items.remove(object);
        } else {
            cards.remove((Card) object);
        }
    }



    // Show cards and items
    public static void print(ArrayList<Card> heroes, ArrayList<Card> cards, ArrayList<Item> items) {
        print("Heroes", heroes);
        printItems(items);
        print("Cards", cards);
    }

    private static void print(String string, ArrayList<Card> cards) {
        int counter = 1;
        System.out.println(string + " : ");
        for (Card card : cards) {
            System.out.printf("%d : ", counter++);
            card.printStats();
        }
    }

    private static void printItems(ArrayList<Item> items) {
        int counter = 1;
        System.out.println("Items :");
        for (Item item : items) {
            System.out.printf("%d : ", counter++);
            item.printStats();
        }
    }

    public Object searchCard(int ID) {
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getCardID() == ID) {
                return heroes.get(i);
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getCardID() == ID) {
                return cards.get(i);
            }
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemID() == ID) {
                return items.get(i);
            }
        }
        return null;
    }

    public List<Object> searchCard(String name){
        List<Object> list = new ArrayList<>();
        for (Card card :
                heroes) {
            if (card.getName().equals(name)){
                list.add(card);
            }
        }
        for (Card card:
                cards) {
            if (card.getName().equals(name)){
                list.add(card);
            }
        }
        for (Item item :
                items) {
            if (item.getName().equals(name)){
                list.add(item);
            }
        }return list;
    }



    public static void help() {
        System.out.println("1. show");
        System.out.println("2. search [card name | item name]");
        System.out.println("3. save");
        System.out.println("4. create deck [deck name]");
        System.out.println("5. delete deck [deck name]");
        System.out.println("6. add [card id | item id | hero id] to deck [deck name]");
        System.out.println("7. remove [card id | item id | hero id] to deck [deck name]");
        System.out.println("8. validate deck [deck name]");
        System.out.println("9. select deck [deck name]");
        System.out.println("10. show all decks");
        System.out.println("11. show deck [deck name]");
        System.out.println("12. help");
        System.out.println("13. exit");
    }
}

package GamePackage;

import java.util.ArrayList;

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
}

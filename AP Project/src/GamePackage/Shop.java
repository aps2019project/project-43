package GamePackage;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }


    public void buyCard(String name) {
        for (int i = 0; i < cards.size() + items.size(); i++) {
            if (i < cards.size() && cards.get(i).getName().equals(name)) {
                if (cards.get(i).getMoneyCost() > World.getInstance().getLoggedAccount().getMoney()) {
                    System.out.println("Insufficient Money");
                } else {
                    if (cards.get(i) instanceof Hero) {
                        World.getInstance().getLoggedAccount().getCollection().addToCollection(cards.get(i));
                        World.getInstance().getLoggedAccount().MoneyTransaction(-cards.get(i).getMoneyCost());
                        System.out.println("Purchase Successful");
                    } else {
                        World.getInstance().getLoggedAccount().getCollection().addToCollection(cards.get(i));
                        World.getInstance().getLoggedAccount().MoneyTransaction(-cards.get(i).getMoneyCost());
                        System.out.println("Purchase Successful");
                    }
                }
            } else if (items.get(i - cards.size()).getName().equals(name)) {
                if (items.get(i).getMoneyCost() > World.getInstance().getLoggedAccount().getMoney()) {
                    System.out.println("Insufficient Money");
                } else if (World.getInstance().getLoggedAccount().getCollection().getItems().size() == Item.getMaxNumItems()) {
                    System.out.println("You Already Have 3 Items");
                } else {
                    World.getInstance().getLoggedAccount().getCollection().addToCollection(items.get(i));
                    World.getInstance().getLoggedAccount().MoneyTransaction(-items.get(i).getMoneyCost());
                    System.out.println("Purchase Successful");
                }
            } else {
                System.out.println("Card/Item Doesn't Exist");
            }
        }
    }

    public void sellCard(int ID) {
        Object object = World.getInstance().getLoggedAccount().getCollection().searchCard(ID);
        if (object != null) {
            World.getInstance().getLoggedAccount().getCollection().removeFromCollection(object);
        } else {
            System.out.println("Card Doesn't Exist");
        }
    }

    public void searchCollection(String name) {
        List<Object> list = World.getInstance().getLoggedAccount().getCollection().searchCard(name);
        if (list.size() == 0) {
            System.out.println("Nothing Found");
        } else {
            for (Object object :
                    list) {
                if (object instanceof Card) {
                    System.out.println(((Card) object).getCardID());
                }
                if (object instanceof Item) {
                    System.out.println(((Item) object).getItemID());
                }
            }
        }
    }

    public void search(String name) {
        for (Card card :
                cards) {
            if (card.getName().equals(name)) {
                System.out.println(card.getCardID());
            }
        }
        for (Item item :
                items) {
            if (item.getName().equals(name)) {
                System.out.println(item.getItemID());
            }
        }
    }

    public void show() {
        for (Card card :
                cards) {
            if (card instanceof Hero) {
                int counter = 1;
                System.out.println(counter++ + card.toString() + " - Buy Cost : " + card.getMoneyCost());
            }
        }
        for (Card card :
                cards) {
            if (card instanceof Minion) {
                int counter = 1;
                System.out.println(counter++ + card.toString() + " - Buy Cost : " + card.getMoneyCost());
            }
        }
        for (Card card :
                cards) {
            if (card instanceof Spell) {
               int counter = 1;
                System.out.println(counter++ + card.toString() + " - Buy Cost : " + card.getMoneyCost());
            }
        }
        for (Item item :
                items) {
            int counter = 1;
            System.out.println(counter++ + item.toString() + " - Buy Cost = " + item.getMoneyCost());
        }

    }

    public void showCollection(){
        Collection collection = World.getInstance().getLoggedAccount().getCollection();
        for (Card card :
                collection.getHeroes()) {
            int counter = 1;
            System.out.println(counter++ + "." + card.toString() + " - Sell Cost : " + card.getMoneyCost());
        }
        for (Item item :
                collection.getItems()) {
            int counter = 1;
            System.out.println(counter++ + "." + item.toString() + " - Sell Cost : " + item.getMoneyCost());
        }
        for (Card card :
                collection.getCards()) {
            int counter = 1;
            System.out.println(counter++ + "." + card.toString() + " - Sell Cost : " + card.getMoneyCost());
        }
    }

}




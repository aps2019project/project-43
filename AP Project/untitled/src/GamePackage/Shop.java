package GamePackage;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }
    private AccountMenu accountMenu = (AccountMenu) AccountMenu.getAccountMenu();


    public void buyCard(String name) {
        Account loggedAccount = accountMenu.getLoggedAccount();
        int playersMoney = accountMenu.getLoggedAccount().getMoney();
        Collection playerCollection = accountMenu.getLoggedAccount().getCollection();

        for (int i = 0; i < cards.size() + items.size(); i++) {
            if (i < cards.size() && cards.get(i).getName().equals(name)) {
                if (cards.get(i).getMoneyCost() > playersMoney) {
                    System.out.println("Insufficient Money");
                } else {
                    if (cards.get(i) instanceof Hero) {
                        playerCollection.addToCollection(cards.get(i));
                        loggedAccount.MoneyTransaction(-cards.get(i).getMoneyCost());
                        System.out.println("Purchase Successful");
                    } else {
                        playerCollection.addToCollection(cards.get(i));
                        loggedAccount.MoneyTransaction(-cards.get(i).getMoneyCost());
                        System.out.println("Purchase Successful");
                    }
                }
            } else if (items.get(i - cards.size()).getName().equals(name)) {
                if (items.get(i).getMoneyCost() > playersMoney) {
                    System.out.println("Insufficient Money");
                } else if (playerCollection.getItems().size() == Item.getMaxNumItems()) {
                    System.out.println("You Already Have 3 Items");
                } else {
                    playerCollection.addToCollection(items.get(i));
                    loggedAccount.MoneyTransaction(-items.get(i).getMoneyCost());
                    System.out.println("Purchase Successful");
                }
            } else {
                System.out.println("Card/Item Doesn't Exist");
            }
        }
    }

    public void sellCard(int ID) {
        Account loggedAccount = accountMenu.getLoggedAccount();
        Collection playerCollection = accountMenu.getLoggedAccount().getCollection();

        Object object = playerCollection.searchCard(ID);
        if (object == null) {
            System.out.println("Card Doesn't Exist");
            return;
        } else if( object instanceof Card){
            loggedAccount.MoneyTransaction(((Card) object).getMoneyCost());
        }else{
            loggedAccount.MoneyTransaction(((Item) object).getMoneyCost());
        }
        playerCollection.removeFromCollection(object);
    }

    public void searchCollection(String name) {
        Collection playerCollection = accountMenu.getLoggedAccount().getCollection();

        List<Object> list = playerCollection.searchCard(name);
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
        Collection playerCollection = accountMenu.getLoggedAccount().getCollection();
        for (Card card :
                playerCollection.getHeroes()) {
            int counter = 1;
            System.out.println(counter++ + "." + card.toString() + " - Sell Cost : " + card.getMoneyCost());
        }
        for (Item item :
                playerCollection.getItems()) {
            int counter = 1;
            System.out.println(counter++ + "." + item.toString() + " - Sell Cost : " + item.getMoneyCost());
        }
        for (Card card :
                playerCollection.getCards()) {
            int counter = 1;
            System.out.println(counter++ + "." + card.toString() + " - Sell Cost : " + card.getMoneyCost());
        }
    }

}




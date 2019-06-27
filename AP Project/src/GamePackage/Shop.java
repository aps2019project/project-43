package GamePackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private AccountMenu accountMenu = (AccountMenu) AccountMenu.getAccountMenu();

    public ArrayList<Card> getCards() {
        return cards;
    }

    void addToCards(Path path){
        cards.add(CardGenerator.cardGenerator(path.toString()));
    }
    void addToHeros(Path path){
        cards.add(CardGenerator.heroGenerator(path.toString()));
    }
    void addToItems(Path path){
        items.add(CardGenerator.itemGenerator(path.toString()));
    }

    Shop(){
        try (Stream<Path> paths = Files.walk(Paths.get("src/CardSamples/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(this::addToCards);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Stream<Path> paths = Files.walk(Paths.get("src/HeroCards/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(this::addToHeros);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Stream<Path> paths = Files.walk(Paths.get("src/ItemCards/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(this::addToItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buyCard(String name) {
        accountMenu = (AccountMenu) AccountMenu.getAccountMenu();
        Account loggedAccount = AccountMenu.getLoggedAccount();
        int playersMoney = AccountMenu.getLoggedAccount().getMoney();
        Collection playerCollection = AccountMenu.getLoggedAccount().getCollection();
        boolean found = false;
        for (int i = 0; i < cards.size() + items.size(); i++) {
            if (i < cards.size()) {
                if(cards.get(i).getName().equalsIgnoreCase(name)) {
                    found = true;
                    if (cards.get(i).getMoneyCost() > playersMoney) {
                        System.out.println("Insufficient Money");
                    } else {
                            Card card = CardGenerator.getClone(cards.get(i));
                            playerCollection.addToCollection(card);
                            loggedAccount.MoneyTransaction(-card.getMoneyCost());
                            card.setOwner(AccountMenu.getLoggedAccount());
                            System.out.println("Purchase Successful");
                    }
                }
            } else if (items.get(i - cards.size()).getName().equalsIgnoreCase(name)) {
                found = true;
                if (items.get(i).getPrice() > playersMoney) {
                    System.out.println("Insufficient Money");
                } else if (playerCollection.getItems().size() == Item.getMaxNumItems()) {
                    System.out.println("You Already Have 3 Items");
                } else {
                    Item item = CardGenerator.getClone(items.get(i-cards.size()));
                    playerCollection.addToCollection(item);
                    loggedAccount.MoneyTransaction(-item.getPrice());
                    System.out.println("Purchase Successful");
                }
            }
        }
        if(!found) {
            System.out.println("Card/Item Doesn't Exist");
        }
    }

    public void sellCard(int ID) {
        Account loggedAccount = AccountMenu.getLoggedAccount();
        Collection playerCollection = AccountMenu.getLoggedAccount().getCollection();

        Object object = playerCollection.searchCard(ID);
        if (object == null) {
            System.out.println("Card Doesn't Exist");
            return;
        } else if( object instanceof Card){
            loggedAccount.MoneyTransaction(((Card) object).getMoneyCost());
            ((Card) object).setOwner(null);
        }else{
            loggedAccount.MoneyTransaction(((Item) object).getPrice());
        }
        playerCollection.removeFromCollection(object);
    }

    public void searchCollection(String name) {
        Collection playerCollection = AccountMenu.getLoggedAccount().getCollection();

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
            if (card.getName().equalsIgnoreCase(name)) {
                System.out.println(card.getCardID());
            }
        }
        for (Item item :
                items) {
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.println(item.getItemID());
            }
        }
    }

    public void show() {
        int counter = 1;
        for (Card card :
                cards) {
            if (card instanceof Hero) {
                System.out.println(counter++ +"."+ card.toString() + " - Buy Cost : " + card.getMoneyCost());
            }
        }
        counter = 1;
        for (Card card :
                cards) {
            if (card instanceof Minion) {
                System.out.println(counter++ +"."+ card.toString() + " - Buy Cost : " + card.getMoneyCost());
            }
        }
        counter = 1;
        for (Card card :
                cards) {
            if (card instanceof Spell) {
                System.out.println(counter++ +"."+ card.toString() + " - Buy Cost : " + card.getMoneyCost());
            }
        }
        counter = 1;
        for (Item item :
                items) {
            System.out.println(counter++ +"."+ item.toString() + " - Buy Cost = " + item.getPrice());
        }

    }

    public void showCollection(){
        Collection playerCollection = AccountMenu.getLoggedAccount().getCollection();
        int counter = 1;
        for (Card card :
                playerCollection.getHeroes()) {
            System.out.println(counter++ + "." + card.toString() + " - Sell Cost : " + card.getMoneyCost());
        }
        counter = 1;
        for (Item item :
                playerCollection.getItems()) {
            System.out.println(counter++ + "." + item.toString() + " - Sell Cost : " + item.getPrice());
        }
        counter = 1;
        for (Card card :
                playerCollection.getCards()) {
            System.out.println(counter++ + "." + card.toString() + " - Sell Cost : " + card.getMoneyCost());
        }
    }

}




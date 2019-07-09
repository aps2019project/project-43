package GamePackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Usable> usableItems = new ArrayList<>();
    private static ArrayList<Collectible> collectibleItems = new ArrayList<>();

    private void addToMinions(Path path){
        cards.add(CardGenerator.minionGenerator(path.toString()));
    }
    private void addToSpells(Path path){
        cards.add(CardGenerator.spellGenerator(path.toString()));
    }
    private void addToHeros(Path path){
        cards.add(CardGenerator.heroGenerator(path.toString()));
    }
    private void addToUsables(Path path){
        usableItems.add(CardGenerator.usableItemGenerator(path.toString()));
    }
    private void addToCollectibles(Path path){
        collectibleItems.add(CardGenerator.collectibleItemGenerator(path.toString()));
    }

    public static Collectible getItem(){
        return CardGenerator.getClone(collectibleItems.get(new Random().nextInt(collectibleItems.size())));
    }

    Shop(){
        try (Stream<Path> paths = Files.walk(Paths.get("src/MinionCards/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(this::addToMinions);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Stream<Path> paths = Files.walk(Paths.get("src/SpellCards/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(this::addToSpells);
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
        try (Stream<Path> paths = Files.walk(Paths.get("src/UsableItemCards/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(this::addToUsables);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Stream<Path> paths = Files.walk(Paths.get("src/CollectibleItemCards/"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(this::addToCollectibles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buyCard(Account acc, String name) {
        Account loggedAccount = acc;
        int playersMoney = loggedAccount.getMoney();
        Collection playerCollection = loggedAccount.getCollection();
        boolean found = false;
        for (int i = 0; i < cards.size() + usableItems.size(); i++) {
            if (i < cards.size()) {
                if(cards.get(i).getName().equalsIgnoreCase(name)) {
                    found = true;
                    if (cards.get(i).getPrice() > playersMoney) {
                        System.out.println("Insufficient Money "+name);
                    } else {
                            Card card = CardGenerator.getClone(cards.get(i));
                            playerCollection.addToCollection(card);
                            loggedAccount.pay(card.getPrice());
                            card.setOwner(loggedAccount);
                            System.out.println("Purchase Successful "+name);
                    }
                }
            } else if (usableItems.get(i - cards.size()).getName().equalsIgnoreCase(name)) {
                found = true;
                if (usableItems.get(i-cards.size()).getPrice() > playersMoney) {
                    System.out.println("Insufficient Money " +name);
                } else if (playerCollection.getItems().size() >= 3) {
                    System.out.println("You Already Have 3 Items");
                } else {
                    Item item = CardGenerator.getClone(usableItems.get(i-cards.size()));
                    playerCollection.addToCollection(item);
                    loggedAccount.pay(item.getPrice());
                    item.setOwner(loggedAccount);
                    System.out.println("Purchase Successful "+name);
                }
            }
        }
        if(!found) {
            System.out.println("Card/Item Doesn't Exist");
        }
    }

    public void sellCard(Account acc, int ID) {
        Account loggedAccount = acc;
        Collection playerCollection = loggedAccount.getCollection();

        GameObject object = playerCollection.getCard(ID);
        if (object == null) {
            System.out.println("Card Doesn't Exist");
            return;
        } else if( object instanceof Card){
            loggedAccount.getPaid(object.getPrice());
            object.setOwner(null);
        }else{
            loggedAccount.getPaid(object.getPrice());
            object.setOwner(null);
        }
        playerCollection.removeFromCollection(object);
    }

    public void search(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                System.out.println(card.getId());
            }
        }
        for (Item item : usableItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.println(item.getId());
            }
        }
    }

    public void show() {
        System.out.println("Heroes :");
        for (Card card : cards) {
            if (card instanceof Hero) {
                System.out.println("\t"+card + " - Buy Cost : " + card.getPrice());
            }
        }
        System.out.println("Items :");
        for (Item item : usableItems) {
            System.out.println("\t"+ item + " - Buy Cost = " + item.getPrice());
        }
        System.out.println("Cards :");
        for (Card card : cards) {
            if (card instanceof Minion) {
                System.out.println("\t"+card+ " - Buy Cost : " + card.getPrice());
            }
        }
        for (Card card : cards) {
            if (card instanceof Spell) {
                System.out.println("\t"+ card + " - Buy Cost : " + card.getPrice());
            }
        }
    }


}




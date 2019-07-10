package GamePackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Stream;

public class Shop {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Usable> usableItems = new ArrayList<>();
    private static ArrayList<Collectible> collectibleItems = new ArrayList<>();

    private void addToMinions(Path path){
        for(int i=0;i<10;i++)
        cards.add(CardGenerator.minionGenerator(path.toString()));
    }
    private void addToSpells(Path path){
        for(int i=0;i<10;i++)
        cards.add(CardGenerator.spellGenerator(path.toString()));
    }
    private void addToHeros(Path path){
        for(int i=0;i<10;i++)
        cards.add(CardGenerator.heroGenerator(path.toString()));
    }
    private void addToUsables(Path path){
        for(int i=0;i<10;i++)
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

    public void buyCard(ClientInfo client, String name) {
        Account loggedAccount = client.getLoggedAccount();
        int playersMoney = loggedAccount.getMoney();
        Collection playerCollection = loggedAccount.getCollection();
        boolean found = false;
        for (int i = 0; i < cards.size() + usableItems.size(); i++) {
            if (i < cards.size()) {
                if(cards.get(i).getName().equalsIgnoreCase(name)) {
                    found = true;
                    if (cards.get(i).getPrice() > playersMoney) {
                        client.sendPrint("Insufficient Money "+name);
                    } else {
                            Card card = CardGenerator.getClone(cards.get(i));
                            card = cards.get(i);
                            cards.remove(i);
                            playerCollection.addToCollection(client, card);
                            loggedAccount.pay(card.getPrice());
                            card.setOwner(loggedAccount);
                            client.sendPrint("Purchase Successful "+name);
                    }
                    break;
                }
            } else if (usableItems.get(i - cards.size()).getName().equalsIgnoreCase(name)) {
                found = true;
                if (usableItems.get(i-cards.size()).getPrice() > playersMoney) {
                    client.sendPrint("Insufficient Money " +name);
                } else if (playerCollection.getItems().size() >= 3) {
                    client.sendPrint("You Already Have 3 Items");
                } else {
                    Item item = CardGenerator.getClone(usableItems.get(i-cards.size()));
                    item = usableItems.get(i-cards.size());
                    usableItems.remove(i-cards.size());
                    playerCollection.addToCollection(client, item);
                    loggedAccount.pay(item.getPrice());
                    item.setOwner(loggedAccount);
                    client.sendPrint("Purchase Successful");
                }
                break;
            }
        }
        if(!found) {
            client.sendPrint("Card/Item Doesn't Exist");
        }
    }

    public void sellCard(ClientInfo client, int ID) {
        Account loggedAccount = client.getLoggedAccount();
        Collection playerCollection = loggedAccount.getCollection();

        GameObject object = playerCollection.getCard(ID);
        if (object == null) {
            client.sendPrint("Card Doesn't Exist");
            return;
        } else if( object instanceof Usable){
            loggedAccount.getPaid(object.getPrice());
            object.setOwner(null);
            usableItems.add((Usable) object);
        } else if( object instanceof Card){
            loggedAccount.getPaid(object.getPrice());
            object.setOwner(null);
            cards.add((Card) object);
        }
        playerCollection.removeFromCollection(object);
        client.sendPrint("Selling Successful");
    }

    public String search(String name) {
        StringBuilder res = new StringBuilder();
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name)) {
                res.append(card.getId()).append("\n");
            }
        }
        for (Item item : usableItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                res.append(item.getId()).append("\n");
            }
        }
        return res.toString();
    }

    public String show() {
        StringBuilder res = new StringBuilder("Heroes :\n");
        for (Card card : new HashSet<>(cards)) {
            if (card instanceof Hero) {
                res.append("\t").append(card).append(" - Buy Cost : ").append(card.getPrice()).append("\n");
            }
        }
        res.append("Items :\n");
        for (Item item : new HashSet<>(usableItems)) {
            res.append("\t").append(item).append(" - Buy Cost = ").append(item.getPrice()).append("\n");
        }
        res.append("Cards :\n");
        for (Card card : new HashSet<>(cards)) {
            if (card instanceof Minion) {
                res.append("\t").append(card).append(" - Buy Cost : ").append(card.getPrice()).append("\n");
            }
        }
        for (Card card : new HashSet<>(cards)) {
            if (card instanceof Spell) {
                res.append("\t").append(card).append(" - Buy Cost : ").append(card.getPrice()).append("\n");
            }
        }
        return res.toString();
    }


}




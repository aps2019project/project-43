package GamePackage;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CardGenerator {

    public static Card cardGenerator(String cardName) {
        ObjectMapper objectMapper = new ObjectMapper();
        Card card ;
        try {
            card = objectMapper.readValue(new File("src\\CardSamples\\" + cardName + ".json"), Card.class); //todo sth wrong
//            card.setCardID();
            return card;
        } catch (IOException e) {
            System.out.println("File doesn't exist or the card name is wrong");
        }
        return null;
    }

    public static Item itemGenerator(String itemName) {
        ObjectMapper objectMapper = new ObjectMapper();
        Item item;
        try {
            item = objectMapper.readValue(new File("src\\CardSamples\\" + itemName + ".json"), Item.class);
//            item.setItemID();
            return item;
        } catch (IOException e) {
            System.out.println("File doesn't exist or the item name is wrong");
        }
        return null;
    }

    public static ArrayList<Card> heroGenerator() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Card> cards = new ArrayList<>();
        File directory = new File("src\\HeroCards");
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File f :
                    files) {
                try {
                   cards.add(objectMapper.readValue(f, Card.class));
                } catch (IOException e) {
                    System.out.println(f.getName() + "cant be generated");
                }
            }
        }else{
            System.out.println("hero directory not found");
        }
        return cards;
    }

}

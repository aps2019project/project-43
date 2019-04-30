package GamePackage;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class CardGenerator {

    public static Card cardGenerator(String cardName){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            objectMapper.readValue(new File("src/CardSamples/" + cardName + ".json"), Card.class);
        }catch(IOException e){
            System.out.println("File doesn't exist or the card name is wrong");
        }
        return null;
    }

    public static Item itemGenerator(String itemName){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            objectMapper.readValue(new File("src/CardSamples/" + itemName +".json"), Item.class);
        }catch(IOException e){
            System.out.println("File doesn't exist or the item name is wrong");
        }
        return null;
    }

}

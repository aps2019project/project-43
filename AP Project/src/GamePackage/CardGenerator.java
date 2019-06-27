package GamePackage;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CardGenerator {

    public static int staticId = 1;

    public static Card cardGenerator(String cardPath){
        ObjectMapper objectMapper = new ObjectMapper();
        Card card;
        try{
            card = objectMapper.readValue(new File(cardPath), Spell.class);
        }catch(IOException ignore){
//            System.out.println(cardPath+ignore);
            try{
                card = objectMapper.readValue(new File(cardPath), Minion.class);
            }catch(IOException e){
                System.out.println("File doesn't exist or the card name is wrong: "+cardPath + e);
                return null;
            }
        }
        card.file= cardPath;
        card.setCardID(staticId++);
        return card;
    }
    public static Card heroGenerator(String heroPath){
        ObjectMapper objectMapper = new ObjectMapper();
        Card card;
        try{
            card = objectMapper.readValue(new File(heroPath), Hero.class);
        }catch(IOException e){
            System.out.println("File doesn't exist or the card name is wrong"+heroPath + e);
            return null;
        }
        card.file=heroPath;
        card.setCardID(staticId++);
        return card;
    }

    public static Item itemGenerator(String itemPath){
        ObjectMapper objectMapper = new ObjectMapper();
        Item item;
        try{
            item = objectMapper.readValue(new File(itemPath), Item.class);
        }catch(IOException e){
            System.out.println("File doesn't exist or the item name is wrong"+itemPath + e);
            return null;
        }
        item.file=itemPath;
        item.setItemID(staticId++);
        return item;
    }

    public static  <T extends Generateble> T getClone(T obj){
        if(obj instanceof Item) return (T) itemGenerator(obj.getFilePath());
        else if(obj instanceof Hero) return (T) heroGenerator(obj.getFilePath());
        else return (T) cardGenerator(obj.getFilePath());
    }
}

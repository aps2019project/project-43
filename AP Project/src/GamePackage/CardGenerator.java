package GamePackage;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CardGenerator {

    private static int staticId = 1;

    public static Minion minionGenerator(String cardPath){
        ObjectMapper objectMapper = new ObjectMapper();
        Minion card;
        try{
            card = objectMapper.readValue(new File(cardPath), Minion.class);
        }catch(IOException e){
            System.out.println("File doesn't exist or the card name is wrong: "+cardPath + e);
            return null;
        }
        card.setFilePath(cardPath);
        card.setId(staticId++);
        return card;
    }
    public static Spell spellGenerator(String cardPath){
        ObjectMapper objectMapper = new ObjectMapper();
        Spell card;
        try{
            card = objectMapper.readValue(new File(cardPath), Spell.class);
        }catch(IOException e){
            System.out.println("File doesn't exist or the card name is wrong: "+cardPath + e);
            return null;
        }
        card.setFilePath(cardPath);
        card.setId(staticId++);
        return card;
    }
    public static Hero heroGenerator(String heroPath){
        ObjectMapper objectMapper = new ObjectMapper();
        Hero card;
        try{
            card = objectMapper.readValue(new File(heroPath), Hero.class);
        }catch(IOException e){
            System.out.println("File doesn't exist or the card name is wrong"+heroPath + e);
            return null;
        }
        card.setFilePath(heroPath);
        card.setId(staticId++);
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
        item.setFilePath(itemPath);
        item.setId(staticId++);
        return item;
    }

    public static  <T extends Generateble> T getClone(T obj){
        if(obj instanceof Item) return (T) itemGenerator(obj.getFilePath());
        else if(obj instanceof Hero) return (T) heroGenerator(obj.getFilePath());
        else if(obj instanceof Spell) return (T) spellGenerator(obj.getFilePath());
        else if(obj instanceof Minion) return (T) minionGenerator(obj.getFilePath());
        return null;
    }
}
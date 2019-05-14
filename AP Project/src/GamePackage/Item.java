package GamePackage;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private String name;
    private String description;
    private int MoneyCost;
    private AtomicInteger itemID = new AtomicInteger();
    private static final int MAX_NUM_ITEMS = 3;

    public static int getMaxNumItems(){
        return MAX_NUM_ITEMS;
    }

    public int getMoneyCost(){
        return MoneyCost;
    }

    public void setItemID(){
        this.itemID.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public int getItemID(){
        return itemID.get();
    }

    public void printStats() {
        System.out.printf("Name : %s - Desc : %s\n", name, description);
    }

    @Override
    public String toString() {
        return "Name : " + name + " - Desc : " + description;
    }
}
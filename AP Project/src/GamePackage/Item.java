
package GamePackage;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private String name;
    private String description;
    private AtomicInteger itemID = new AtomicInteger();

    public void setItemID(){
        this.itemID.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public AtomicInteger getItemID(){
        return itemID;
    }

    public void printStats() {
        System.out.printf("Name : %s - Desc : %s\n", name, description);
    }
}
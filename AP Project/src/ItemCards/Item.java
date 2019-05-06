/*
package GamePackage;

public class Item extends Card{
    private int price;
    private int coolDown;
    private Spell specialPower;

    public int getPrice() {
        return price;
    }

    public int getCoolDown() {
        return coolDown;
    }


    public Spell getSpecialPower() {
        return specialPower;
    }

    public Item(String name, int price, int moneyCost, int manaCost, Buff buff, String description, int coolDown,
                Spell specialPower) {
        super(name, moneyCost, manaCost, buff, description);
        this.price = price;
        this.specialPower = specialPower;
        this.coolDown = coolDown;
    }

    public void printStats() {
        System.out.printf("Name : %s - Desc : %s\n", getName(), getSpecialPower());
    }
}
*/

package GamePackage;

public class Item {
    private String name;
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void printStats() {
        System.out.printf("Name : %s - Desc : %s\n", name, description);
    }
}


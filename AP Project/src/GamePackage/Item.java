package GamePackage;

public class Item implements Generateble{
    public String name;
    public String description;
    public int price;
    public int changeAP;
    public int changeHP;
    public int changeMana;
    public int cooldown;
    private int itemID;
    public SpellTarget target;
    public boolean unused;
    public String file;

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    private Buff buff;

    private static final int MAX_NUM_ITEMS = 3;

    public static int getMaxNumItems(){
        return MAX_NUM_ITEMS;
    }

    public int getPrice(){
        return price;
    }

    public void setItemID(int id){
        this.itemID = id;
    }

    public String getName() {
        return name;
    }

    public int getItemID(){
        return itemID;
    }

    @Override
    public String toString() {
        return itemID+": "+"Name : " + name + " - Desc : " + description;
    }

    @Override
    public String getFilePath() {
        return file;
    }
}
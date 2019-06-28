package GamePackage;

public class Item extends GameObject{
    public int changeAP;
    public int changeHP;
    public int changeMana;
    public int cooldown;
    public SpellTarget target;
    public boolean unused;

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

    @Override
    public String toString() {
        return getId()+": "+"Name : " + getName() + " - Desc : " + getInfo();
    }

}
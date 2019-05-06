package GamePackage;

public class Usable extends Item {
    private Buff buff;
    private int changeAP;
    private int changeHP;
    private int changeMana;
    private int cooldown;
    private int price;
    private SpellTarget target;

    public Usable(String name, String description, int price, int changeAP, int changeHP, int changeMana, int cooldown, SpellTarget target, Buff buff) {
        super(name, description);
        this.changeAP = changeAP;
        this.changeHP = changeHP;
        this.changeMana = changeMana;
        this.cooldown = cooldown;
        this.buff = buff;
        this.target = target;
        this.price = price;
    }

    public Buff getBuff() {
        return buff;
    }

    public int getChangeAP() {
        return changeAP;
    }

    public int getChangeHP() {
        return changeHP;
    }

    public int getChangeMana() {
        return changeMana;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getPrice() {
        return price;
    }

    public SpellTarget getTarget() {
        return target;
    }

    public boolean isUnused() {
        return unused;
    }

    private boolean unused = true;

}

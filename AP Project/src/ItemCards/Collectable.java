package GamePackage;

public class Collectable extends Item {

    private Buff buff;
    private int changeAP;
    private int changeHP;
    private int changeMana;
    private int cooldown;
    private SpellTarget target;
    private boolean unused = true;

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

    public SpellTarget getTarget() {
        return target;
    }

    public boolean isUnused() {
        return unused;
    }

    public void setUnused(boolean unused) {
        this.unused = unused;
    }

    public Collectable(String name, String description, int changeAP, int changeHP,int changeMana, int cooldown, Buff buff, SpellTarget target) {
        super(name, description);
        this.changeAP = changeAP;
        this.changeHP = changeHP;
        this.changeMana = changeMana;
        this.cooldown = cooldown;
        this.buff = buff;
        this.target = target;
    }
}

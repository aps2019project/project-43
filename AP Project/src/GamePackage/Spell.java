package GamePackage;

public class Spell extends Card {
    private int coolDown;
//    private Buff effect;
    private SpellTarget target;
    private int changeHP;
    private int changeAP;
    private int targetArea;

     @Override
    public void printStats() {
        System.out.printf("Type : Spell - Name : %s - MP : %d - Desc : %s\n"
                , getName(), getManaCost());
    }

//    public Spell(String effect) {
//        try {
//            this.effect = Buff.valueOf(effect);
//        } catch (Exception e) {
//            this.effect = null;
//        }
//    }

    public Spell() {
    }

    public Spell(String name, int moneyCost, int manaCost, Buff buff, String info, int coolDown, SpellTarget target, int changeHP, int changeAP, int targetArea) {
        super(name, moneyCost, manaCost, buff, info);
        this.coolDown = coolDown;
        this.target = target;
        this.changeHP = changeHP;
        this.changeAP = changeAP;
        this.targetArea = targetArea;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public SpellTarget getTarget() {
        return target;
    }

    public int getChangeHP() {
        return changeHP;
    }

    public int getChangeAP() {
        return changeAP;
    }

    public int getTargetArea() {
        return targetArea;
    }
}

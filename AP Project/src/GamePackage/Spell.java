package GamePackage;

public class Spell extends Card {
    private int coolDown;
//    private Buff effect;
    private SpellTarget target;
    private int changeHP;
    private int changeAP;
    private int targetArea;

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

    @Override
    public String toString() {
        return getCardID() +": Type : Spell - Name : " + getName() + "- MP : " + getManaCost() + "- Desc : " + getInfo();
    } //Use for Show Command in Shop

}

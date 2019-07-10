package GamePackage;

public class Spell extends Card {
    private int coolDown;
    private SpellTarget target;
    private int changeHP;
    private int changeAP;
    private int changeMana=0;
    private int targetArea;
    private int whichTurn;
    private Buff buff;

    public Buff getBuff() {
        return buff;
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

    public int getWhichTurn() {
        return whichTurn;
    }

    public int getChangeMana() {
        return changeMana;
    }

    @Override
    public String toString() {
        return getId() +": Type : Spell - Name : " + getName() + "- MP : " + getManaCost() + "- Desc : " + getInfo();
    } //Use for Show Command in Shop

    @Override
    public String showCardInfo() {
        return ("Spell:")+("\n\tName: "+getName())+("\n\tMP: "+getManaCost())+("\n\tCost: "+getPrice())+("\n\tDesc: "+getInfo());
    }

    public void setBuff(Buff buff, int changeHP, int changeAP) {
        this.buff=buff;
        this.changeHP=changeHP;
        this.changeAP=changeAP;
    }
}

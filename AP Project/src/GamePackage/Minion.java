package GamePackage;

public class Minion extends Force {

    private ActivateTime activateTime;

    public ActivateTime getActivateTime() {
        return activateTime;
    }

    @Override
    public String toString() {
        return getId()+": Type : Minion - Name : " + getName() + " - Class : " + getTroopType().toString().toLowerCase() + " - AP : " +
                getAp() + " - HP : " + getHealth() + " - MP : " + getManaCost() + " - Special Power : " + getInfo();
    }

    @Override
    public String showCardInfo() {
        return ("Minion:")+("\n\tName: "+getName())+("\n\tHP: "+ getHealth() + "\n\tAP: "+getAp()+"\n\tMP: "+ getManaCost())+("\n\tCombo-ability: ")+("\n\tCost: "+getPrice())+("\n\tDesc: "+getInfo());
    }
}

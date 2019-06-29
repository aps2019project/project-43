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
    public void showCardInfo() {
        System.out.println("Minion:");
        System.out.println("Name: "+getName());
        System.out.println("HP: "+ getHealth() + " AP: "+getAp()+" MP: "+ getManaCost());
        System.out.println("combo-ability: ");
        System.out.println("Cost: "+getPrice());
        System.out.println("Desc: "+getInfo());
    }
}

package GamePackage;

public class Hero extends Force {

    private int coolDown;
    private int cooling=0;

    public int getCoolDown() {
        return coolDown;
    }

    @Override
    public void endTurn() {
        super.endTurn();
        if(cooling>0)cooling--;
    }

    public boolean isCooling(){
        return cooling>0;
    }

    public void useSpecialPower(){
        cooling=coolDown;
    }

    @Override
    public String toString() {
        return String.format("%d: Name : %s - AP : %d - HP : %d - " +
                        "Class : %s - Special Power : %s\n", getId(), getName(), getAp(), getHealth()
                , getTroopType().toString().toLowerCase(), getInfo());
    }

    @Override
    public void showCardInfo() {
        System.out.println("Hero:");
        System.out.println("Name: "+getName());
        System.out.println("Cost: "+getPrice());
        System.out.println("Desc: "+getInfo());
    }
}

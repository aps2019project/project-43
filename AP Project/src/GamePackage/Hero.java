package GamePackage;

public class Hero extends Force {

    private int coolDown;

    public int getCoolDown() {
        return coolDown;
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

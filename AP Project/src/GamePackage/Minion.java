package GamePackage;

public class Minion extends Card {

    private int hp;
    private int mp;
    private AttackType troop;

    @Override
    public void printStats() {
    }

    public void attackTroops() {
        troop.attack();
    }
}

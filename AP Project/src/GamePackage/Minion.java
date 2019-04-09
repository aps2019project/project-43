package GamePackage;

public class Minion extends Card {

    private int hp;
    private int ap;
    private AttackType troopType;
    private Spell specialPower = new Spell();

    @Override
    public void printStats() {
    }

    public void attackTroops() {
        troopType.attack();
    }
}

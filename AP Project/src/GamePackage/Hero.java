package GamePackage;

public class Hero extends Card {
    private int hp;
    private int ap;
    private Spell specialPower;
    private AttackType troopType;
    private String specialPowerDescription;

    @Override
    public void printStats() {
        System.out.printf("Name : %s - AP : %d - HP : %d - " +
                        "Class : %s - Special Power : %s\n", getName(), ap, hp
                , troopType.toString().toLowerCase(), specialPowerDescription);
    }

    public void castSpell() {
    }
}

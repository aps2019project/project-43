package GamePackage;

public class Hero extends Attacker {

    public int coolDown;
    public Spell specialPower;

    @Override
    public String toString() {
        return String.format("%d: Name : %s - AP : %d - HP : %d - " +
                        "Class : %s - Special Power : %s\n",getCardID(), getName(), ap, hp
                , troopType.toString().toLowerCase(), description);
    }

    public void castSpell() {
    }
}

package GamePackage;

public class Hero extends Card {
    private int hp;
    private int ap;
    private int attackRange;
    private int coolDown;
    private Spell specialPower;
    private AttackType troopType;

    public int getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    public AttackType getTroopType() {
        return troopType;
    }

    public Hero(String name, int moneyCost, int manaCost, Buff buff, String info,
                int hp, int ap, int attackRange, int coolDown, String troopType, Spell specialPower) {
        super(name, moneyCost, manaCost, buff, info);
        this.ap = ap;
        this.hp = hp;
        this.attackRange = attackRange;
        this.troopType = AttackType.valueOf(troopType.toUpperCase());
        this.specialPower = specialPower;
        this.coolDown = coolDown;
    }

    @Override
    public void printStats() {
        System.out.printf("Name : %s - AP : %d - HP : %d - " +
                        "Class : %s - Special Power : %s\n", getName(), ap, hp
                , troopType.toString().toLowerCase(), getSpecialPower());
    }

    public void castSpell() {
    }
}

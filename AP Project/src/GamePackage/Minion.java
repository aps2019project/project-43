package GamePackage;

import java.util.ArrayList;

public class Minion extends Attacker {
    private ArrayList<Spell> specialPower = new ArrayList<>();

    public AttackType getTroopType() {
        return troopType;
    }

    public ArrayList<Spell> getSpecialPower() {
        return specialPower;
    }

    public Minion() {
    }

//    public Minion(String name, int moneyCost, int manaCost, Buff buff, String info
//            , int hp, int ap, int attackRange, String troopType, ArrayList<Spell> specialPower) {
//        super(name, moneyCost, manaCost, buff, info);
//        this.hp = hp;
//        this.ap = ap;
//        this.troopType = AttackType.valueOf(troopType.toUpperCase());
//        this.specialPower = specialPower;
//        this.attackRange = attackRange;
//    }

    @Override
    public String toString() {
        return getCardID()+": Type : Minion - Name : " + getName() + " - Class : " + troopType.toString().toLowerCase() + " - AP : " +
                ap + " - HP : " + hp + " - MP : " + getManaCost() + " - Special Power : " + getInfo();
    }


}

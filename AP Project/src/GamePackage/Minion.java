package GamePackage;


public class Minion extends Card {
    private int hp;
    private int ap;
    private int attackRange;
    private AttackType troopType;
    private Spell specialPower;
//    private String specialPowerDescription;
    private boolean isStunned = false;
    private boolean isDisarmed = false;



    public int getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public AttackType getTroopType() {
        return troopType;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public boolean isDisarmed() {
        return isDisarmed;
    }


    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public void setDisarmed(boolean disarmed) {
        isDisarmed = disarmed;
    }


    public Minion() {
    }

    public Minion(String name, int moneyCost, int manaCost, Buff buff, String info
            , int hp, int ap, int attackRange, String troopType, Spell specialPower) {
        super(name, moneyCost, manaCost, buff, info);
        this.hp = hp;
        this.ap = ap;
        this.troopType = AttackType.valueOf(troopType.toUpperCase());
        this.specialPower = specialPower;
        this.attackRange = attackRange;
    }


    @Override
    public void printStats() {
        System.out.println("Type : Minion - Name : " +  getName() + " - Class : " + troopType.toString().toLowerCase() + " - AP : " + ap +
                " - HP : " + hp + " - MP : " + getManaCost() + " - Special Power : " + getInfo());
    } //Use for Show Deck in Collection

//    @Override
    public void sellDetails() {
        System.out.println("Type : Minion - Name : " + getName() + " - Class : " + troopType.toString().toLowerCase() + " - AP : " +
                    ap + " - HP : " + hp + " - MP : " + getManaCost() + " - Special Power : " + getInfo() + " - Sell Cost : " + getMoneyCost());
    } //Use for Show Command in Shop

//    @Override
    public void buyDetails() {
        System.out.println("Type : Minion - Name : " + getName() + " - Class : " + troopType.toString().toLowerCase() + " - AP : " +
                ap + " - HP : " + hp + " - MP : " + getManaCost() + " - Special Power : " + getInfo() +" - Buy Cost : " + getMoneyCost());
    }

    public void attack() {
        troopType.attack();
    }

    public void defend(){
        troopType.defend();
    }

}

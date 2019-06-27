package GamePackage;

public class Attacker extends Card{

    public AttackType troopType;
    public int hp;
    public int ap;
    public int attackRange;
    public int turnMoved=-1;
    public int turnAttacked=-1;
    private boolean isStunned = false;
    private boolean isDisarmed = false;

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

    public void getHit(int ap){
        hp-=ap;
    }

    public void attack(int turn) {
        turnAttacked=turn;
        turnMoved=turn;
    }

    public void die() {
        getCardLocation().setBusy(false);
        setCardLocation(null);
    }

    public int getHp() {
        return hp;
    }
    public int getAp() {
        return ap;
    }
}

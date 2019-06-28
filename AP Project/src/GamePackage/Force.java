package GamePackage;

import java.util.ArrayList;

public abstract class Force extends Card {

    private AttackType troopType;
    private int hp;
    private int ap;
    private int attackRange;
    private int turnMoved=-1;
    private int turnAttacked=-1;
    private boolean isStunned = false;
    private boolean isDisarmed = false;
    private ArrayList<Spell> effected = new ArrayList<>();
    private ArrayList<Spell> specialPower = new ArrayList<>();
    private ActivateTime activateTime;
    private int damage = 0;

    public ActivateTime getActivateTime() {
        return activateTime;
    }

    public void startGame(){
        turnMoved=-1;
        turnAttacked=-1;
        damage=0;
        effected.clear();
        isStunned=false;
        isDisarmed=false;
        setLocation(null);
    }

    public AttackType getTroopType() {
        return troopType;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public ArrayList<Spell> getSpecialPower() {
        return specialPower;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public void setDisarmed(boolean disarmed) {
        isDisarmed = disarmed;
    }

    public void getHit(int ap){
        damage+=ap;
    }

    public int getHp() {
        return hp;
    }

    public void attack(int turn) {
        turnAttacked=turn;
    }

    public boolean canMove(int turn){
        return turnAttacked!=turn && turnMoved!=turn && !isStunned;
    }
    public boolean canAttack(int turn){
        return turnAttacked!=turn && !isStunned;
    }
    public boolean canDefend(){
        return !isDisarmed;
    }

    public void die() {
        getLocation().setForce(null);
        setLocation(null);
    }

    public void setLocation(Cell cell){
        if(getLocation()!=null) getLocation().setForce(null);
        if(cell!=null) cell.setForce(this);
        super.setLocation(cell);
    }

    public int getHealth() {
        return hp-damage;
    }

    public int getAp() {
        return ap;
    }


    public void move(Cell cell, int turn) {
        // todo if a card can move more than two cells
        if(getLocation().getDistance(cell) > 2
                ||!canMove(turn)){
            System.out.println("Invalid target");
            return;
        }
        setLocation(cell);
        turnMoved=turn;
    }

    public static void printForces(ArrayList<Force> forces) {
        for (Force force: forces) {
            System.out.println(force.getId() + " : " + force.getName() +
                    "health : " + force.getHealth() + ", location : (" + force.getLocation().getX() +
                    ", " + force.getLocation().getY() + "), power : " + force.getAp());
        }
    }

}

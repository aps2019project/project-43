package GamePackage;

import java.util.ArrayList;

public abstract class Force extends Card {

    private AttackType troopType;
    private int hp;
    private int ap;
    private int attackRange;
    private int turnMoved=-1;
    private int turnAttacked=-1;
    private ArrayList<Effect> effects = new ArrayList<>();
    private ArrayList<Effect> continuousEffects = new ArrayList<>();
    private ArrayList<Spell> specialPower = new ArrayList<>();
    private int damage = 0;
    private int wasInPoisonousCell = 3;

    void addEffect(Spell spell){

        if(spell.getBuff()==Buff.KILL){
            die();
        }else if(spell.getBuff()==Buff.ATTACK){
            getHit(spell.getChangeAP());
        }else if(spell.getBuff()==Buff.REMOVE){
            for(int i=0;i<effects.size();i++){
                Effect effect=effects.get(i);
                if(getOwner()==spell.getOwner()){
                    switch (effect.getBuff()){
                        case WEAKNESS:
                        case POISON:
                        case STUN:
                        case DISARM:
                        case POISON_ON_ATTACK:
                            effects.remove(effect);
                            i--;
                    }
                }else{
                    switch (effect.getBuff()){
                        case HOLY:
                        case POWER:
                        case POWER_CONT:
                        case HOLY_CONT:
                            effects.remove(effect);
                            i--;
                    }
                }
            }
        }
        else effects.add(new Effect(spell));
    }


    public void endTurn() {
        if(wasInPoisonousCell>0){
            damage+=1;
            wasInPoisonousCell--;
        }
        for(int i=0;i<effects.size();i++){
            for(Effect effect: getLocation().getEffects()){
                if(effect.getBuff()==Buff.CELL_ON_FIRE){
                    damage+=effect.getSpell().getChangeHP();
                }
                if(effect.getBuff()==Buff.POISONOUS_CELL){
                    wasInPoisonousCell=2;
                    damage+=1;
                }
            }
            for(Effect effect: effects){
                if(effect.getBuff()==Buff.POISON || effect.getBuff()==Buff.POISON_ON_ATTACK){
                    damage+=effect.getSpell().getChangeHP();
                }
            }
            for(Effect effect: continuousEffects){
                if(!effects.contains(effect)) effects.add(effect);
            }
            if(effects.get(i).endTurn()) effects.remove(i--);
        }
    }

    public void startGame(){
        turnMoved=-1;
        turnAttacked=-1;
        damage=0;
        effects.clear();
        continuousEffects.clear();
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

    public void getHit(int ap){
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.HOLY || effect.getBuff()==Buff.HOLY_CONT){
                if(ap>=effect.getSpell().getChangeHP()) ap-=effect.getSpell().getChangeHP();
            }
        }
        for(Effect effect: getLocation().getEffects()){
            if(effect.getBuff()==Buff.HOLY_CELL){
                if(ap>=effect.getSpell().getChangeHP()) ap-=effect.getSpell().getChangeHP();
            }
        }
        damage+=ap;
    }

    public int getHp() {
        return hp;
    }

    private boolean isStunned(){
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.STUN){
                return true;
            }
        }
        return false;
    }

    private boolean isDisarmed(){
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.DISARM || effect.getBuff()==Buff.MADDNESS){
                return true;
            }
        }
        return false;
    }

    private boolean canMove(int turn){
        return turnAttacked!=turn && turnMoved!=turn && !isStunned();
    }

    private boolean canAttack(int turn){
        return turnAttacked!=turn && !isStunned();
    }

    boolean canDefend(){
        return !isDisarmed();
    }

    public void die() {
        damage=hp;
        effects.clear();
        getLocation().setForce(null);
        setLocation(null);
    }

    public void setLocation(Cell cell){
        if(getLocation()!=null) getLocation().setForce(null);
        if(cell!=null) cell.setForce(this);
        super.setLocation(cell);
    }

    public int getHealth() {
        int extra=0;
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.POWER || effect.getBuff()==Buff.MADDNESS || effect.getBuff()==Buff.POWER_CONT){
                extra+=effect.getSpell().getChangeHP();
            }
            if(effect.getBuff()==Buff.WEAKNESS){
                extra-=effect.getSpell().getChangeHP();
            }
        }
        if(hp-damage+extra<0)return 0;
        return hp-damage+extra;
    }

    public int getAp() {
        int extra=0;
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.POWER || effect.getBuff()==Buff.MADDNESS || effect.getBuff()==Buff.POWER_CONT){
                extra+=effect.getSpell().getChangeAP();
            }
            if(effect.getBuff()==Buff.WEAKNESS){
                extra-=effect.getSpell().getChangeAP();
            }
        }
        if(ap+extra<0)return 0;
        return ap+extra;
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


    void attack(Force target, int turn, boolean defend){
        if(!canAttack(turn)){
            System.out.println("Card with id "+ getId() +" can't attack");
            return;
        }
        if(getTroopType().isInRangeForAttack(this, target)){
            System.out.println("opponent minion is unavailable for attack");
            return;
        }
        turnAttacked=turn;
        target.getHit(getAp());
        if(defend && target.canDefend() && target.getTroopType().isInRangeForDefend(target, this)){
            getHit(target.getAp());
        }
        for(Spell spell: specialPower){
            if(spell.getBuff()==Buff.POISON_ON_ATTACK){
                target.addEffect(spell);
            }
        }
    }

    public static void printForces(ArrayList<Force> forces) {
        for (Force force: forces) {
            System.out.println(force.getId() + " : " + force.getName() +
                    "health : " + force.getHealth() + ", location : (" + force.getLocation().getX() +
                    ", " + force.getLocation().getY() + "), power : " + force.getAp());
        }
    }

}

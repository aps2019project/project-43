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
    private Flag flag = null;
    private int hadFlag=0;

    public int getHadFlag() {
        return hadFlag;
    }

    void addEffect(Spell spell){

        if(spell.getBuff()==Buff.KILL){
            die();
        }else if(spell.getBuff()==Buff.ATTACK || spell.getBuff()==Buff.ATTACK_ON_INSERT){
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
                        case WEAKNESS_ON_ATTACK:
                            effects.remove(effect);
                            i--;
                            break;
                        case WEAKH_HOLYA:
                            spell.setBuff(Buff.HOLY, spell.getChangeAP(), spell.getChangeAP());
                            break;
                        case WEAKH_POWERA:
                            spell.setBuff(Buff.POWER, 0,spell.getChangeAP());
                            break;
                        case MADDNESS:
                            spell.setBuff(Buff.POWER, 0, spell.getChangeAP());
                            break;
                    }
                }else{
                    switch (effect.getBuff()){
                        case HOLY:
                        case POWER:
                        case POWER_CONT:
                        case POWER_ON_DEATH:
                        case HOLY_CONT:
                        case HOLY_ON_SPAWN:
                            effects.remove(effect);
                            i--;
                            break;
                        case WEAKH_HOLYA:
                            spell.setBuff(Buff.WEAKNESS, spell.getChangeHP(), spell.getChangeHP());
                            break;
                        case WEAKH_POWERA:
                            spell.setBuff(Buff.WEAKH_POWERA, spell.getChangeHP(), 0);
                            break;
                        case MADDNESS:
                            spell.setBuff(Buff.DISARM, 0, 0);
                            break;
                        case POWERA_HEALTH:
                            spell.setBuff(Buff.HEALTH, spell.getChangeHP(), 0);
                            break;
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
        if(flag!=null){
            hadFlag++;
        }
    }

    public void startGame(){
        turnMoved=-1;
        turnAttacked=-1;
        damage=0;
        effects.clear();
        continuousEffects.clear();
        flag=null;
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

    private void getHit(int ap, boolean useHoly){
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.DISHOLY){
                if(ap>=effect.getSpell().getChangeAP()) ap+=effect.getSpell().getChangeAP();
            }
        }
        if(useHoly){
            for(Effect effect: effects){
                if(effect.getBuff()==Buff.HOLY || effect.getBuff()==Buff.HOLY_CONT || effect.getBuff()==Buff.WEAKH_HOLYA || effect.getBuff()==Buff.HOLY_ON_SPAWN){
                    if(ap>=effect.getSpell().getChangeAP()) ap-=effect.getSpell().getChangeAP();
                }
            }
            for(Effect effect: getLocation().getEffects()){
                if(effect.getBuff()==Buff.HOLY_CELL){
                    if(ap>=effect.getSpell().getChangeHP()) ap-=effect.getSpell().getChangeHP();
                }
            }
        }
        damage+=ap;
    }
    private void getHit(int ap){
        getHit(ap, true);
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
            if(effect.getBuff()==Buff.DISARM || effect.getBuff()==Buff.MADDNESS || effect.getBuff()==Buff.DISARM_RH_ON_ATTACK || effect.getBuff()==Buff.DISARM_ON_ATTACK){
                return true;
            }
        }
        return false;
    }

    public boolean canMove(int turn){
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
        if(cell!=null && cell.getFlag()!=null){
            liftFlag(cell.getFlag());
            cell.loseFlag();
        }
        if(cell!=null && cell.getItem()!=null){
            getOwner().getMainDeck().addItem(cell.getItem());
            cell.setItem(null);
        }
    }

    public int getHealth() {
        int extra=0;
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.POWER || effect.getBuff()==Buff.POWER_NOTDISPEL ||effect.getBuff()==Buff.MADDNESS || effect.getBuff()==Buff.POWER_CONT || effect.getBuff()==Buff.POWER_ON_DEATH || effect.getBuff()==Buff.POWERA_HEALTH || effect.getBuff()==Buff.HEALTH){
                extra+=effect.getSpell().getChangeHP();
            }
            if(effect.getBuff()==Buff.WEAKNESS ||effect.getBuff()==Buff.WEAKH_HOLYA || effect.getBuff()==Buff.WEAKH_POWERA||effect.getBuff()==Buff.WEAKNESS_NOTDISPEL || effect.getBuff()==Buff.WEAKNESS_ON_ATTACK){
                extra-=effect.getSpell().getChangeHP();
            }
        }
        if(hp-damage+extra<0)return 0;
        return hp-damage+extra;
    }

    public int getAp() {
        int extra=0;
        for(Effect effect: effects){
            if(effect.getBuff()==Buff.POWER || effect.getBuff()==Buff.POWER_NOTDISPEL|| effect.getBuff()==Buff.MADDNESS || effect.getBuff()==Buff.POWER_CONT || effect.getBuff()==Buff.WEAKH_POWERA || effect.getBuff()==Buff.POWER_ON_DEATH || effect.getBuff()==Buff.POWERA_HEALTH){
                extra+=effect.getSpell().getChangeAP();
            }
            if(effect.getBuff()==Buff.WEAKNESS || effect.getBuff()==Buff.WEAKNESS_NOTDISPEL || effect.getBuff()==Buff.WEAKNESS_ON_ATTACK){
                extra-=effect.getSpell().getChangeAP();
            }
        }
        if(ap+extra<0)return 0;
        return ap+extra;
    }

    public void move(Cell cell, int turn) {
        if(getLocation().getDistance(cell) > 2
                ||!canMove(turn)){
            System.out.println("Invalid target");
            return;
        }
        setLocation(cell);
        turnMoved=turn;
    }

    private boolean hasEffect(Buff buff){
        for(Spell spell: specialPower){
            if(spell.getBuff()==buff)return true;
        }
        return false;
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
        target.getHit(getAp(), !hasEffect(Buff.UNHOLY));
        if(defend && target.canDefend() && target.getTroopType().isInRangeForDefend(target, this)){
            getHit(target.getAp());
        }
        for(Spell spell: specialPower){
            if(spell.getBuff()==Buff.POISON_ON_ATTACK){
                target.addEffect(spell);
            }
        }
    }

    private void liftFlag(Flag flag){
        hadFlag=0;
        this.flag=flag;
    }

    void loseFlag(){
        flag=null;
    }

    Flag getFlag(){
        return flag;
    }

    public static void printForces(ArrayList<Force> forces) {
        for (Force force: forces) {
            System.out.println(force.getId() + " : " + force.getName() +
                    ", health : " + force.getHealth() + ", location : (" + force.getLocation().getX() +
                    ", " + force.getLocation().getY() + "), power : " + force.getAp());
        }
    }

}

package GamePackage;

public class Effect{
    private Spell spell;
    private int turnRemained;
    private int toBegin;

    Effect(Spell spell){
        this.spell = spell;
        this.turnRemained = spell.getCoolDown();
        this.toBegin=spell.getWhichTurn();
    }

    public boolean endTurn(){
        if(toBegin>0){
            toBegin--;
        }else if(turnRemained>0){
            turnRemained--;
        }
        return turnRemained == 0;
    }

    public Spell getSpell() {
        return toBegin==0?spell:null;
    }

    public Buff getBuff(){
        return toBegin==0?spell.getBuff():null;
    }
}

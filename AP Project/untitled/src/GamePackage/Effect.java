package GamePackage;

public class Effect{
    private Spell spell;
    private int turnRemained;

    Effect(Spell spell){
        this.spell = spell;
        this.turnRemained = spell.getCoolDown();
    }

    public boolean endTurn(){
        return --turnRemained == 0;
    }

    public Spell getSpell() {
        return spell;
    }

    public Buff getBuff(){
//        return spell.getBuff();
        return null;
    }
}

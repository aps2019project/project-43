package GamePackage;

public class Spell extends Card {
    private int coolDown;
    private Buff effect;

    @Override
    public void printStats() {
    }

    public Spell(String effect) {
        try {
            this.effect = Buff.valueOf(effect);
        } catch (Exception e) {
            this.effect = null;
        }
    }
}

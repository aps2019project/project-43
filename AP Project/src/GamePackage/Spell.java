package GamePackage;

public class Spell extends Card {
    private int coolDown;
    private Buff effect;
    private String description;

    @Override
    public void printStats() {
        System.out.printf("Type : Spell - Name : %s - MP : %d - Desc : %s\n"
                , getName(), getManaCost(), description);
    }

    public Spell(String effect) {
        try {
            this.effect = Buff.valueOf(effect);
        } catch (Exception e) {
            this.effect = null;
        }
    }
}

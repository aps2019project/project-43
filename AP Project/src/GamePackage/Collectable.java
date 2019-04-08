package GamePackage;

public class Collectable extends Item {
    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    private String effect;

    public boolean isUnused() {
        return unused;
    }

    public void setUnused(boolean unused) {
        this.unused = unused;
    }

    private boolean unused = true;
}

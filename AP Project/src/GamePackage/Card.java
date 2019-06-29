package GamePackage;

public abstract class Card extends GameObject{
    private int manaCost;

    public int getManaCost() {
        return manaCost;
    }

    public abstract void showCardInfo();
}

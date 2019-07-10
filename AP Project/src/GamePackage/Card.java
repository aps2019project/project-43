package GamePackage;

public abstract class Card extends GameObject{
    private int manaCost;

    public int getManaCost() {
        return manaCost;
    }

    public abstract String showCardInfo();

    @Override
    public boolean equals(Object o) {
        return o != null && this.getClass() == o.getClass() && this.getId() == ((Card) o).getId();
    }
}

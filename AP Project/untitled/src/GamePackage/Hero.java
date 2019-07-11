package GamePackage;

import javafx.scene.layout.StackPane;

public class Hero extends Card {
    private int hp;
    private int ap;
    private Spell specialPower;
    private AttackType troopType;
    private String specialPowerDescription;

    @Override
    public void printStats() {
        System.out.printf("Name : %s - AP : %d - HP : %d - " +
                        "Class : %s - Special Power : %s\n", getName(), ap, hp
                , troopType.toString().toLowerCase(), specialPowerDescription);
    }

    @Override
    public String toString() {
        return null;
    }

    public void castSpell() {
    }

    @Override
    public StackPane showCard() {
        StackPane root = new StackPane();
        root.getStyleClass().add("card-background");



        return root;
    }
}

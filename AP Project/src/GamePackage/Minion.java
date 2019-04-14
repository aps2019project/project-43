package GamePackage;

import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.UUID;

public class Minion extends Card {
    public static Minion currentMinion;
    private int hp;
    private int ap;
    private AttackType troopType;
    private TroopSample troopSample;
    private Spell specialPower;
    private String specialPowerDescription;

    public int getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public AttackType getTroopType() {
        return troopType;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    @Override
    public void printStats() {
        System.out.printf("Type : Minion - Name : %s - Class : %s - AP : %d" +
                        " - HP : %d - MP : %d - Special Power : %s\n", getName()
                , troopType.toString().toLowerCase(), ap, hp, getManaCost(), specialPowerDescription);
    }

    public void init() {
        troopSample.doSomething();
        currentMinion.printStats();
    }

    public void attackTroops() {
        troopType.attack();
    }

    public Minion(TroopSample troopSample) {
        this.troopSample = troopSample;
        Minion.currentMinion = this;
    }

    public Minion(String name, int moneyCost, int manaCost, int hp, int ap, String troopType, Spell specialPower) {
        super(name, UUID.randomUUID(), moneyCost, manaCost);
        this.hp = hp;
        this.ap = ap;
        this.troopType = AttackType.valueOf(troopType.toUpperCase());
        this.specialPower = specialPower;
    }

    public static void setMinion(String name, int moneyCost, int manaCost, int hp, int ap, String troopType, Spell specialPower) {
        currentMinion = new Minion(name, moneyCost, manaCost, hp, ap, troopType, specialPower);
    }

    public Minion getInstance() {
        return this;
    }

    enum TroopSample {
        PERSIAN_ARCHER {
            @Override
            public void doSomething() {
                try {
                    fileReader = new FileReader("MinionSamples/PersianArcher.txt");
                    char[] c = new char[100];
                    fileReader.read(c);
                    String[] s = new String(c).split(" ");
                    setMinion("Persian Archer", Integer.parseInt(s[0])
                            , Integer.parseInt(s[1]), Integer.parseInt(s[2])
                            , Integer.parseInt(s[3]), s[4].toUpperCase(), null);
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        };

        public void doSomething() {
        }

        FileReader fileReader;
    }
}

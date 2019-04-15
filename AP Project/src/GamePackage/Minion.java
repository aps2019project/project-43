package GamePackage;

import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class Minion extends Card {
    private int hp;
    private int ap;
    private int attackRange;
    private AttackType troopType;
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

    public static Minion initMinion(TroopSample troopSample) {
        return troopSample.doSomething();
    }

    public void attackTroops() {
        troopType.attack();
    }

    public Minion(String name, int moneyCost, int manaCost
            , int hp, int ap, int attackRange, String troopType, Spell specialPower) {
        super(name, UUID.randomUUID(), moneyCost, manaCost);
        this.hp = hp;
        this.ap = ap;
        this.troopType = AttackType.valueOf(troopType.toUpperCase());
        this.specialPower = specialPower;
        this.attackRange = attackRange;
    }

    enum TroopSample {
        PERSIAN_ARCHER {
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Persian Archer"
                            , "src/MinionSamples/PersianArcher.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        PERSIAN_SWORDSMAN {
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Persian Swordsman"
                            , "src/MinionSamples/PersianSwordsman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        PERSIAN_PIKESMAN {
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Persian Pikesman"
                            , "src/MinionSamples/PersianPikesman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        PERSIAN_HORSEMAN{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Persian Horseman"
                            , "src/MinionSamples/PersianHorseman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        PERSIAN_PAHLAVAN{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Persian Pahlavan"
                            , "src/MinionSamples/PersianPahlavan.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        PERSIAN_SEPAHSALAR{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Persian Sepahsalar"
                            , "src/MinionSamples/PersianSepahsalar.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        TURANIAN_ARCHER{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Turanian Archer"
                            , "src/MinionSamples/TuranianArcher.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        TURANIAN_GHOLABI{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Turanian Gholabi"
                            , "src/MinionSamples/TuranianGholabi.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        TURANINAN_PIKESMAN{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Turanian Pikesman"
                            , "src/MinionSamples/TuranianPikesman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        TURANIAN_SPY{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Turanian Spy"
                            , "src/MinionSamples/TuranianSpy.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        TURANIAN_GURZMAN{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Turanian Gurzman"
                            , "src/MinionSamples/TuranianGurzman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        TURANIAN_PRINCE{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Turanian Prince"
                            , "src/MinionSamples/TuranianPrince.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        BLACK_GIANT{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Black Giant"
                            , "src/MinionSamples/BlackGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        ROCK_THROWER_GIANT{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Rock Thrower Giant"
                            , "src/MinionSamples/RockThrowerGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        EAGLE{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Eagle"
                            , "src/MinionSamples/Eagle.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        HOGRIDER_GIANT{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("HogRider Giant"
                            , "src/MinionSamples/HogRiderGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        ONE_EYE_GIANT{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("One Eye Giant"
                            , "src/MinionSamples/OneEyeGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        POISONOUS_SNAKE{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Poisonous Snake"
                            , "src/MinionSamples/PoisonousSnake.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        DRAGON{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Dragon"
                            , "src/MinionSamples/Dragon.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        },
        LION{
            @Override
            public Minion doSomething() {
                try {
                    return readDataFromFile("Lion"
                            , "src/MinionSamples/Lion.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                    return null;
                }
            }
        }

        ;

        private static Minion readDataFromFile(String name, String filePath) throws IOException {
            FileReader fileReader = new FileReader(filePath);
            char[] c = new char[100];
            fileReader.read(c);
            String[] s = new String(c).split("\\$");
            return new Minion(name, Integer.parseInt(s[0])
                    , Integer.parseInt(s[1]), Integer.parseInt(s[2])
                    , Integer.parseInt(s[3]), Integer.parseInt(s[5])
                    , s[4].toUpperCase()
                    , new Spell(s[7].toUpperCase()));// $ set 1 for melee minions
        }

        public abstract Minion doSomething();
    }
}

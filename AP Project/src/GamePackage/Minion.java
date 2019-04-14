package GamePackage;

import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

public class Minion extends Card {
    public static Minion currentMinion;
    private int hp;
    private int ap;
    private int attackRange;
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

    public Minion(String name, int moneyCost, int manaCost
            , int hp, int ap, int attackRange, String troopType, Spell specialPower) {
        super(name, UUID.randomUUID(), moneyCost, manaCost);
        this.hp = hp;
        this.ap = ap;
        this.troopType = AttackType.valueOf(troopType.toUpperCase());
        this.specialPower = specialPower;
        this.attackRange = attackRange;
    }

    public static void setMinion(String name, int moneyCost, int manaCost
            , int hp, int ap, int attackRange, String troopType, Spell specialPower) {
        currentMinion = new Minion(name, moneyCost, manaCost, hp, ap, attackRange, troopType, specialPower);
    }

    enum TroopSample {
        PERSIAN_ARCHER {
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Persian Archer"
                            , "src/MinionSamples/PersianArcher.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        PERSIAN_SWORDSMAN {
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Persian Swordsman"
                            , "src/MinionSamples/PersianSwordsman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        PERSIAN_PIKESMAN {
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Persian Pikesman"
                            , "src/MinionSamples/PersianPikesman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        PERSIAN_HORSEMAN{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Persian Horseman"
                            , "src/MinionSamples/PersianHorseman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        PERSIAN_PAHLAVAN{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Persian Pahlavan"
                            , "src/MinionSamples/PersianPahlavan.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        PERSIAN_SEPAHSALAR{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Persian Sepahsalar"
                            , "src/MinionSamples/PersianSepahsalar.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        TURANIAN_ARCHER{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Turanian Archer"
                            , "src/MinionSamples/TuranianArcher.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        TURANIAN_GHOLABI{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Turanian Gholabi"
                            , "src/MinionSamples/TuranianGholabi.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        TURANINAN_PIKESMAN{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Turanian Pikesman"
                            , "src/MinionSamples/TuranianPikesman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        TURANIAN_SPY{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Turanian Spy"
                            , "src/MinionSamples/TuranianSpy.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        TURANIAN_GURZMAN{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Turanian Gurzman"
                            , "src/MinionSamples/TuranianGurzman.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        TURANIAN_PRINCE{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Turanian Prince"
                            , "src/MinionSamples/TuranianPrince.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        BLACK_GIANT{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Black Giant"
                            , "src/MinionSamples/BlackGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        ROCK_THROWER_GIANT{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Rock Thrower Giant"
                            , "src/MinionSamples/RockThrowerGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        EAGLE{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Eagle"
                            , "src/MinionSamples/Eagle.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        HOGRIDER_GIANT{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("HogRider Giant"
                            , "src/MinionSamples/HogRiderGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        ONE_EYE_GIANT{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("One Eye Giant"
                            , "src/MinionSamples/OneEyeGiant.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        POISONOUS_SNAKE{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Poisonous Snake"
                            , "src/MinionSamples/PoisonousSnake.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        DRAGON{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Dragon"
                            , "src/MinionSamples/Dragon.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        },
        LION{
            @Override
            public void doSomething() {
                try {
                    readDataFromFile("Lion"
                            , "src/MinionSamples/Lion.txt");
                } catch (IOException ignore) {
                    System.out.println("file not found");
                }
            }
        }

        ;

        private static void readDataFromFile(String name, String filePath) throws IOException {
            FileReader fileReader = new FileReader(filePath);
            char[] c = new char[100];
            fileReader.read(c);
            String[] s = new String(c).split("\\$");
            setMinion(name, Integer.parseInt(s[0])
                    , Integer.parseInt(s[1]), Integer.parseInt(s[2])
                    , Integer.parseInt(s[3]), Integer.parseInt(s[5])
                    , s[4].toUpperCase()
                    , new Spell(s[7].toUpperCase()));// $ set 0 for melee minions
        }

        public void doSomething() {
        }
    }
}

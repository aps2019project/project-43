
package GamePackage;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import static GamePackage.SpellTarget.*;

public class HeroConstructor {
    public void main() {
        ObjectMapper objectMapper = new ObjectMapper();
        Hero[] h = new Hero[10];
        Spell[] s = new Spell[10];

        s[0] = new Spell(null, 0, 0, Buff.POWER, null, 100, ALLY_FORCE, 0, 4, 13);
        h[0] = new Hero("WhiteBeast", 8000, 1, Buff.POWER,
                "Power buff with 4 increment in ap permanently", 50, 4, 0, 2, "Melee", s[0]);

        s[1] = new Spell(null, 0, 0, Buff.STUN, null, 1, ENEMY_FORCE, 0, 0, 13);
        h[1] = new Hero("Phoenix", 9000, 5, Buff.STUN,
                "Stun all enemies forces for one round", 50, 4, 0, 8, "Melee", s[1]);

        s[2] = new Spell(null, 0, 0, Buff.DISARM, null, 1, ENEMY_FORCE, 0, 0, 13);
        h[2] = new Hero("Seven-headedDragon", 8000, 0, Buff.DISARM,
                "Disarm one player", 50, 4, 0, 1, "Melee", s[2]);

        s[3] = new Spell(null, 0, 0, Buff.STUN, null, 1, ENEMY_FORCE, 0, 0, 13);
        h[3] = new Hero("Rakhsh", 8000, 1, Buff.STUN,
                "Stun one enemy force for one round", 50, 4, 0, 2, "Melee", s[3]);

        s[4] = new Spell(null, 0, 0, Buff.POISON, null, 3, ALLY_FORCE, 0, 0, 13);
        h[4] = new Hero("Zahhak", 10000, 0, Buff.POISON,
                "Poison the target for three rounds", 50, 2, 0, 0, "Melee", s[4]);

        s[5] = new Spell(null, 0, 0, Buff.HOLY, null, 3, CELL, 0, 0, 13);
        h[5] = new Hero("Kaveh", 8000, 1, Buff.HOLY,
                "Holy a cell for three rounds", 50, 4, 0, 3, "Melee", s[5]);

        s[6] = new Spell(null, 0, 0, Buff.POWER, null, 100, ENEMY_FORCE, 0, 4, 2);
        h[6] = new Hero("Arash", 10000, 2, Buff.POWER,
                "All forces in hero's row hit with four", 30, 2, 6, 2, "Ranged", s[6]);//should I add enemy hero too?

        s[7] = new Spell(null, 0, 0, Buff.REMOVE, null, 100, ALLY_FORCE, 0, 0, 13);
        h[7] = new Hero("Myth", 11000, 1, Buff.REMOVE,
                "Dispel one enemy force", 40, 3, 3, 2, "Ranged", s[7]);

        s[8] = new Spell(null, 0, 0, Buff.HOLY, null, 3, ALLY_HERO, 0, 4, 0);
        h[8] = new Hero("Esfandyar", 12000, 0, Buff.HOLY,
                "It has three holy buff continuously", 35, 3, 3, 0, "Hybrid", s[8]);

        h[9] = new Hero("Rostam", 8000, 0, Buff.ON_ATTACK,
                " ", 55, 7, 4, 0, "Hybrid", null);//todo


        for (int i = 0; i < 10; i++) {
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/CardSamples/" + h[i].getName() + ".json"), h[i]);
            } catch (Exception e) {

            }
        }
    }
}


package GamePackage;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

import static GamePackage.SpellTarget.*;
import static GamePackage.SpellTarget.ALLY_MINION;

public class UsableMain {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Usable[] usables = new Usable[11];

        usables[0] = new Usable("WisdomCrown", "Increase Mana for first three round of one force", 300,  0, 6, 3,  3, ANYTHING, null);
        usables[1] = new Usable("HugeShield", "Activate 12 Holy buff in ally Hero", 4000,  0, 6, 0,  0, ALLY_HERO, Buff.HOLY);
        usables[2] = new Usable("DamonArch", "Disarm enemy force for one round, only for ranged and hybrid", 30000,  0, 0, 0,  1, ENEMY_FORCE, Buff.DISARM);//todo
        usables[3] = new Usable("PhoenixFeather", "Decrease enemy's ap by 2, only for ranged and hybrid", 3500,  2, 0, 0,  100, ENEMY_HERO, null);//todo
        usables[4] = new Usable("TerrorHood", "decrease random enemy force's ap by 2 with weakness buff for one round", 5000,  2, 0, 0,  1, ENEMY_FORCE, Buff.WEAKNESS);
        usables[5] = new Usable("KingWisdom", "Each round mana increase by 1", 9000,  0, 0, 1,  100, ALLY_FORCE, null);
        usables[6] = new Usable("AssassinationDagger", "Decrease enemy's hero hp by 1 whenever adding new ally troop", 15000,  0, 1, 0,  100, ENEMY_HERO, null);
        usables[7] = new Usable("PoisonousDagger", "Apply poison buff on random enemy force when ally forces hit something for one round", 7000,  0, 0, 0,  1, ENEMY_FORCE, Buff.POISON);
        usables[8] = new Usable("ShockHammer", "Ally hero when attack, disarm enemy's force for one round", 15000,  0, 0, 0,  1, ENEMY_FORCE, Buff.DISARM);
        usables[9] = new Usable("SoulEater", "When an ally force killed, random ally force's ap increase by 1 with power buff", 25000,  1, 0, 0,  100, ALLY_FORCE, Buff.POWER);
        usables[10] = new Usable("Baptism", "Each minion get Holy buff for 2 rounds when spawning", 20000,  0, 0, 0,  2, ALLY_MINION, Buff.HOLY);


        for (int i = 0; i < 20; i++) {
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/CardSamples/" + usables[i].getName() + ".json"), usables[i]);
            } catch (Exception e) {

            }
        }
    }
}

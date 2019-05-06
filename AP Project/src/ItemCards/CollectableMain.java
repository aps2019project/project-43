package GamePackage;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

import static GamePackage.SpellTarget.*;
import static GamePackage.SpellTarget.ANYTHING;

public class CollectableMain {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Collectable[] collectables = new Collectable[10];

        collectables[0] = new Collectable("Nooshdaro", "Increase HP by 6 for random force", 0, 6, 0,  100, null, ANYTHING);
        collectables[1] = new Collectable("Two-headedArrow", "Increase AP by 6 for random ranged or hybrid force", 2, 0, 0,100, null, ANYTHING);
        collectables[2] = new Collectable("Elixir", "Increase HP by 3 and a power buff with 3 increment in AP for a minion", 0, 3, 0, 100, Buff.POWER, ALLY_MINION);
        collectables[3] = new Collectable("ManaElixir", "Increase Mana by 3 for the next round", 2, 0, 3, 0, null, ANYTHING);
        collectables[4] = new Collectable("InvulnerabilityElixir", "Set Holy buff on a random ally force for two rounds", 0, 0, 0, 2, Buff.HOLY, ALLY_FORCE);
        collectables[5] = new Collectable("DeathCurse", "Give 8 AP to a random minion which can use it before death randomly on its nearest force", 8, 0, 0,100, null, ALLY_MINION);
        collectables[6] = new Collectable("RandomDamage", "Increase random force's AP by 2", 2, 0, 0, 100, null, ANYTHING);
        collectables[7] = new Collectable("BladesOfAgility", "Increase random force's AP by 6", 6, 0, 0, 100, null, ANYTHING);
        collectables[8] = new Collectable("ChineseSword", "Give 5 AP to melee troops", 5, 0, 0, 100, null, ANYTHING);//todo



        for (int i = 0; i < 20; i++) {
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/CardSamples/" + collectables[i].getName() + ".json"), collectables[i]);
            } catch (Exception e) {

            }
        }
    }
}

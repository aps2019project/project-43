package GamePackage;

import java.util.ArrayList;

public class ComputerAccount extends Account {
    ComputerAccount(String deck) {
        super("Computer", "");
        getPaid(1000000);
        createDeck("first");
        createDeck("second");
        createDeck("third");
        setMainDeck(deck);

        //hero1
        getShop().buyCard(this, "WhiteBeast");
        addObjectToDeck("WhiteBeast", "first");

        //item1
        getShop().buyCard(this, "WisdomCrown");
        addObjectToDeck("wisdomcrown", "first");

        //spell1
        getShop().buyCard(this, "totaldisarm");
        getShop().buyCard(this, "lightingbolt");
        getShop().buyCard(this, "alldisarm");
        getShop().buyCard(this, "allpoison");
        getShop().buyCard(this, "dispel");
        getShop().buyCard(this, "sacrifice");
        getShop().buyCard(this, "shock");
        addObjectToDeck("totaldisarm", "first");
        addObjectToDeck("lightingbolt", "first");
        addObjectToDeck("alldisarm", "first");
        addObjectToDeck("allpoison", "first");
        addObjectToDeck("dispel", "first");
        addObjectToDeck("sacrifice", "first");
        addObjectToDeck("shock", "first");

        //minion1
        getShop().buyCard(this, "persianarcher");
        getShop().buyCard(this, "turanianpikesman");
        getShop().buyCard(this, "turanianrockerman");
        getShop().buyCard(this, "turanianrockerman");
        getShop().buyCard(this, "blackgiant");
        getShop().buyCard(this, "oneeyegiant");
        getShop().buyCard(this, "poisonoussnake");
        getShop().buyCard(this, "giantsnake");
        getShop().buyCard(this, "whitewolf");
        getShop().buyCard(this, "greatwizard");
        getShop().buyCard(this, "nanesarma");
        getShop().buyCard(this, "siavash");
        getShop().buyCard(this, "arzhang");
        addObjectToDeck("persianarcher", "first");
        addObjectToDeck("turanianpikesman", "first");
        addObjectToDeck("turanianrockerman", "first");
        addObjectToDeck("turanianrockerman", "first");
        addObjectToDeck("blackgiant", "first");
        addObjectToDeck("oneeyegiant", "first");
        addObjectToDeck("poisonoussnake", "first");
        addObjectToDeck("giantsnake", "first");
        addObjectToDeck("whitewolf", "first");
        addObjectToDeck("greatwizard", "first");
        addObjectToDeck("nanesarma", "first");
        addObjectToDeck("siavash", "first");
        addObjectToDeck("arzhang", "first");


        //hero2
        getShop().buyCard(this, "Zahhak");
        addObjectToDeck("Zahhak", "second");

        //item2
        getShop().buyCard(this, "SoulEater");
        addObjectToDeck("SoulEater", "second");

        //spell2
        getShop().buyCard(this, "areadispel");
        getShop().buyCard(this, "empower");
        getShop().buyCard(this, "godstrength");
        getShop().buyCard(this, "poisonlake");
        getShop().buyCard(this, "madness");
        getShop().buyCard(this, "healthwithprofit");
        getShop().buyCard(this, "kingsguard");
        addObjectToDeck("areadispel", "second");
        addObjectToDeck("empower", "second");
        addObjectToDeck("godstrength", "second");
        addObjectToDeck("poisonlake", "second");
        addObjectToDeck("madness", "second");
        addObjectToDeck("healthwithprofit", "second");
        addObjectToDeck("kingsguard", "second");

        //minion2
        getShop().buyCard(this, "persianswordman");
        getShop().buyCard(this, "persianpikesman");
        getShop().buyCard(this, "pahlavan");
        getShop().buyCard(this, "turanianrockerman");
        getShop().buyCard(this, "turanianprince");
        getShop().buyCard(this, "eagle");
        getShop().buyCard(this, "eagle");
        getShop().buyCard(this, "dragon");
        getShop().buyCard(this, "panther");
        getShop().buyCard(this, "jen");
        getShop().buyCard(this, "give");
        getShop().buyCard(this, "iraj");
        getShop().buyCard(this, "kinggiant");
        addObjectToDeck("persianswordman", "second");
        addObjectToDeck("persianpikesman", "second");
        addObjectToDeck("pahlavan", "second");
        addObjectToDeck("turanianrockerman", "second");
        addObjectToDeck("turanianprince", "second");
        addObjectToDeck("eagle", "second");
        addObjectToDeck("eagle", "second");
        addObjectToDeck("dragon", "second");
        addObjectToDeck("panther", "second");
        addObjectToDeck("jen", "second");
        addObjectToDeck("give", "second");
        addObjectToDeck("iraj", "second");
        addObjectToDeck("kinggiant", "second");


        //hero3
        getShop().buyCard(this, "Arash");
        addObjectToDeck("Arash", "third");

        //item3
        getShop().buyCard(this, "TerrorHood");
        addObjectToDeck("TerrorHood", "third");

        //spell3
        getShop().buyCard(this, "hellfire");
        getShop().buyCard(this, "alldisarm");
        getShop().buyCard(this, "dispel");
        getShop().buyCard(this, "powerup");
        getShop().buyCard(this, "allpower");
        getShop().buyCard(this, "allattack");
        getShop().buyCard(this, "weakening");
        addObjectToDeck("hellfire", "third");
        addObjectToDeck("alldisarm", "third");
        addObjectToDeck("dispel", "third");
        addObjectToDeck("powerup", "third");
        addObjectToDeck("allpower", "third");
        addObjectToDeck("allattack", "third");
        addObjectToDeck("weakening", "third");

        //minion3
        getShop().buyCard(this, "sepahsalar");
        getShop().buyCard(this, "turanianarcher");
        getShop().buyCard(this, "turanianspy");
        getShop().buyCard(this, "rockthrowergiant");
        getShop().buyCard(this, "hogridergiant");
        getShop().buyCard(this, "hogridergiant");
        getShop().buyCard(this, "lion");
        getShop().buyCard(this, "wolf");
        getShop().buyCard(this, "wizard");
        getShop().buyCard(this, "wildhug");
        getShop().buyCard(this, "piran");
        getShop().buyCard(this, "bahman");
        getShop().buyCard(this, "greatgiant");
        addObjectToDeck("sepahsalar", "third");
        addObjectToDeck("turanianarcher", "third");
        addObjectToDeck("turanianspy", "third");
        addObjectToDeck("rockthrowergiant", "third");
        addObjectToDeck("hogridergiant", "third");
        addObjectToDeck("hogridergiant", "third");
        addObjectToDeck("lion", "third");
        addObjectToDeck("wolf", "third");
        addObjectToDeck("wizard", "third");
        addObjectToDeck("wildhug", "third");
        addObjectToDeck("piran", "third");
        addObjectToDeck("bahman", "third");
        addObjectToDeck("greatgiant", "third");
    }



    @Override
    public void doYourTurn(Battle battle) {

        ArrayList<Card> cards = battle.getTurnAccount().getMainDeck().cardsToBeInserted(battle.getMana());
        for (Card card : cards) {
            if(battle.getMana()<card.getManaCost())continue;
            int x,y;
            if(card instanceof Minion){
                ArrayList<Cell> cells = new ArrayList<>();
                for (Force force : battle.getMyCardsInGame()) {
                    cells.addAll(battle.getAdjacentCells(force.getLocation()));
                }
                cells.removeIf(Cell::isBusy);
                Cell cell = cells.get(battle.random.nextInt(cells.size()));
                x=cell.getX();
                y=cell.getY();
            } else {
                x=2;
                y=4;
            }
            Main.exec("insert "+card.getName()+" in ("+x+", "+y+")");
        }

        for(Force force: battle.getMyCardsInGame()){
            if(force.canMove(battle.getTurn())){
                ArrayList<Cell> cells = battle.getCells(force.getLocation().getX()-2,force.getLocation().getY()-2,5);
                cells.removeIf(cell -> cell.getDistance(force.getLocation())>2 || cell==force.getLocation() || cell.isBusy());
                if(cells.size()>0){
                    Main.exec("select "+force.getId());
                    Cell cell = cells.get(battle.random.nextInt(cells.size()));
                    Main.exec("move to (["+cell.getX()+"], ["+cell.getY()+"])");
                }
            }
        }

        for (Force force : battle.getMyCardsInGame()) {
            Main.exec("select "+force.getId());
            for (Force force1 : battle.getOpponentCardsInGame()) {
                Main.exec("attack "+force1.getId());
            }
        }

        Main.exec("use special power ("+2+", "+4+")");

        Main.exec("end turn");
    }
}

package GamePackage;

import java.util.ArrayList;

public class ComputerAccount extends Account {

    private Shop shop = new Shop();
    private Shop getShop(){
        return shop;
    }

    ComputerAccount(String deck, ComputerClientInfo client) {
        super("Computer", "");
        this.setClient(client);
        client.setLoggedAccount(this);
        getPaid(1000000);
        createDeck("first");
        createDeck("second");
        createDeck("third");
        setMainDeck(deck);

        //hero1
        getShop().buyCard(getClient(), "WhiteBeast");
        addObjectToDeck("WhiteBeast", "first");

        //item1
        getShop().buyCard(getClient(), "WisdomCrown");
        addObjectToDeck("wisdomcrown", "first");

        //spell1
        getShop().buyCard(getClient(), "totaldisarm");
        getShop().buyCard(getClient(), "lightingbolt");
        getShop().buyCard(getClient(), "alldisarm");
        getShop().buyCard(getClient(), "allpoison");
        getShop().buyCard(getClient(), "dispel");
        getShop().buyCard(getClient(), "sacrifice");
        getShop().buyCard(getClient(), "shock");
        addObjectToDeck("totaldisarm", "first");
        addObjectToDeck("lightingbolt", "first");
        addObjectToDeck("alldisarm", "first");
        addObjectToDeck("allpoison", "first");
        addObjectToDeck("dispel", "first");
        addObjectToDeck("sacrifice", "first");
        addObjectToDeck("shock", "first");

        //minion1
        getShop().buyCard(getClient(), "persianarcher");
        getShop().buyCard(getClient(), "turanianpikesman");
        getShop().buyCard(getClient(), "turanianrockerman");
        getShop().buyCard(getClient(), "turanianrockerman");
        getShop().buyCard(getClient(), "blackgiant");
        getShop().buyCard(getClient(), "oneeyegiant");
        getShop().buyCard(getClient(), "poisonoussnake");
        getShop().buyCard(getClient(), "giantsnake");
        getShop().buyCard(getClient(), "whitewolf");
        getShop().buyCard(getClient(), "greatwizard");
        getShop().buyCard(getClient(), "nanesarma");
        getShop().buyCard(getClient(), "siavash");
        getShop().buyCard(getClient(), "arzhang");
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
        getShop().buyCard(getClient(), "Zahhak");
        addObjectToDeck("Zahhak", "second");

        //item2
        getShop().buyCard(getClient(), "SoulEater");
        addObjectToDeck("SoulEater", "second");

        //spell2
        getShop().buyCard(getClient(), "areadispel");
        getShop().buyCard(getClient(), "empower");
        getShop().buyCard(getClient(), "godstrength");
        getShop().buyCard(getClient(), "poisonlake");
        getShop().buyCard(getClient(), "madness");
        getShop().buyCard(getClient(), "healthwithprofit");
        getShop().buyCard(getClient(), "kingsguard");
        addObjectToDeck("areadispel", "second");
        addObjectToDeck("empower", "second");
        addObjectToDeck("godstrength", "second");
        addObjectToDeck("poisonlake", "second");
        addObjectToDeck("madness", "second");
        addObjectToDeck("healthwithprofit", "second");
        addObjectToDeck("kingsguard", "second");

        //minion2
        getShop().buyCard(getClient(), "persianswordman");
        getShop().buyCard(getClient(), "persianpikesman");
        getShop().buyCard(getClient(), "pahlavan");
        getShop().buyCard(getClient(), "turanianrockerman");
        getShop().buyCard(getClient(), "turanianprince");
        getShop().buyCard(getClient(), "eagle");
        getShop().buyCard(getClient(), "eagle");
        getShop().buyCard(getClient(), "dragon");
        getShop().buyCard(getClient(), "panther");
        getShop().buyCard(getClient(), "jen");
        getShop().buyCard(getClient(), "give");
        getShop().buyCard(getClient(), "iraj");
        getShop().buyCard(getClient(), "kinggiant");
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
        getShop().buyCard(getClient(), "Arash");
        addObjectToDeck("Arash", "third");

        //item3
        getShop().buyCard(getClient(), "TerrorHood");
        addObjectToDeck("TerrorHood", "third");

        //spell3
        getShop().buyCard(getClient(), "hellfire");
        getShop().buyCard(getClient(), "alldisarm");
        getShop().buyCard(getClient(), "dispel");
        getShop().buyCard(getClient(), "powerup");
        getShop().buyCard(getClient(), "allpower");
        getShop().buyCard(getClient(), "allattack");
        getShop().buyCard(getClient(), "weakening");
        addObjectToDeck("hellfire", "third");
        addObjectToDeck("alldisarm", "third");
        addObjectToDeck("dispel", "third");
        addObjectToDeck("powerup", "third");
        addObjectToDeck("allpower", "third");
        addObjectToDeck("allattack", "third");
        addObjectToDeck("weakening", "third");

        //minion3
        getShop().buyCard(getClient(), "sepahsalar");
        getShop().buyCard(getClient(), "turanianarcher");
        getShop().buyCard(getClient(), "turanianspy");
        getShop().buyCard(getClient(), "rockthrowergiant");
        getShop().buyCard(getClient(), "hogridergiant");
        getShop().buyCard(getClient(), "hogridergiant");
        getShop().buyCard(getClient(), "lion");
        getShop().buyCard(getClient(), "wolf");
        getShop().buyCard(getClient(), "wizard");
        getShop().buyCard(getClient(), "wildhug");
        getShop().buyCard(getClient(), "piran");
        getShop().buyCard(getClient(), "bahman");
        getShop().buyCard(getClient(), "greatgiant");
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
        try {

            ArrayList<Card> cards = battle.getTurnAccount().getMainDeck().cardsToBeInserted(battle.getMana());
            for (Card card : cards) {
                if (battle.getMana() < card.getManaCost()) continue;
                int x, y;
                if (card instanceof Minion) {
                    ArrayList<Cell> cells = new ArrayList<>();
                    for (Force force : battle.getMyCardsInGame()) {
                        cells.addAll(battle.getAdjacentCells(force.getLocation()));
                    }
                    cells.removeIf(Cell::isBusy);
                    Cell cell = cells.get(battle.random.nextInt(cells.size()));
                    x = cell.getX();
                    y = cell.getY();
                } else {
                    x = 2;
                    y = 4;
                }
                getClient().exec("insert " + card.getName() + " in (" + x + ", " + y + ")");
            }

            for (Force force : battle.getMyCardsInGame()) {
                if (force.canMove(battle.getTurn())) {
                    ArrayList<Cell> cells = battle.getCells(force.getLocation().getX() - 2, force.getLocation().getY() - 2, 5);
                    cells.removeIf(cell -> cell.getDistance(force.getLocation()) > 2 || cell == force.getLocation() || cell.isBusy());
                    if (cells.size() > 0) {
                        getClient().exec("select " + force.getId());
                        Cell cell = cells.get(battle.random.nextInt(cells.size()));
                        getClient().exec("move to ([" + cell.getX() + "], [" + cell.getY() + "])");
                    }
                }
            }

            for (Force force : battle.getMyCardsInGame()) {
                getClient().exec("select " + force.getId());
                for (Force force1 : battle.getOpponentCardsInGame()) {
                    getClient().exec("attack " + force1.getId());
                }
            }

            getClient().exec("use special power (" + 2 + ", " + 4 + ")");

            getClient().exec("end turn");
        }catch (Exception ignored){}
    }
}

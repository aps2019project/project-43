package GamePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

    private String mode; //
    private Account[] players = new Account[2];
    private int[] mana = new int[]{2,2};
    private Force selectedCard;
    private ArrayList<Card> graveyard1 =new ArrayList<>();
    private ArrayList<Card> graveyard2 =new ArrayList<>();
    private int numberOfFlags;
    private int award = 1000;
    Random random = new Random();

    public String showBoard(){
        StringBuilder res = new StringBuilder("\n");
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                Cell cell = cells[i][j];
                if(!cells[i][j].isBusy()){
                    if(cell.getFlag()!=null) res.append("F  ");
                    else if(cell.getItem()!=null) res.append("I  ");
                    else res.append("O  ");
                }
                else if(cell.getForce() instanceof Hero){
                    if(cell.getForce().getOwner()==players[0]) res.append("H1 ");
                    else res.append("H2 ");
                }
                else if(cell.getForce() instanceof Minion){
                    if(cell.getForce().getOwner()==players[0]) res.append("M1 ");
                    else res.append("M2 ");
                }
            }
            res.append("\n");
        }
        res.append(getTurnAccount().getMainDeck().getHero().getLocation());
        return res.toString();
    }

    public String showGameInfo(){
        StringBuilder res = new StringBuilder();
        res.append("player1 mana: ").append(mana[0]).append("\n");
        res.append("player2 mana: ").append(mana[1]).append("\n");
        switch (mode) {
            case "hero":
                res.append("player1 hero health: ").append(players[0].getMainDeck().getHero().getHealth()).append("\n");
                res.append("player2 hero health: ").append(players[1].getMainDeck().getHero().getHealth()).append("\n");
                break;
            case "flag6":
                res.append(printFlags()).append('\n');
                break;
            case "flag1/2":
                res.append(printFlags()).append('\n');
                break;
        }
        res.append("turn: player").append(getIndexTurn() + 1).append(": ").append(getTurnAccount().getUsername()).append("\n");
        res.append(showBoard());
        return res.toString();
    }

    private String printFlags() {
        StringBuilder res = new StringBuilder();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(getCell(i,j).getFlag()!=null){
                    res.append("flag in position: ").append(i).append(", ").append(j).append("\n");
                }else if(getCell(i,j).isBusy()&&getCell(i,j).getForce().getFlag()!=null){
                    res.append("flag is in ").append(getCell(i, j).getForce().getOwner().getUsername()).append("'s hand\n");
                }
            }
        }
        return res.toString();
    }

    void endTurn(ClientInfo client){
        getTurnAccount().getMainDeck().addNextCard();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                cells[i][j].endTurn();
            }
        }
        for(Force force:getMyCardsInGame()){
            force.endTurn();
            checkIfDead(force);
        }
        for(Force force:getOpponentCardsInGame()){
            force.endTurn();
            checkIfDead(force);
        }
        selectedCard = null;
        turn++;
        mana[0] = mana[1] = turn / 2 + 2;
        if (turn > 14) mana[0] = mana[1] = 9;
        if(players[0].getMainDeck().getItem()!=null){
            if(turn<=2*players[0].getMainDeck().getItem().getCoolDown()){
                mana[0]+=players[0].getMainDeck().getItem().getChangeMana();
            }
        }
        if(players[1].getMainDeck().getItem()!=null){
            if(turn<=2*players[1].getMainDeck().getItem().getCoolDown()){
                mana[1]+=players[1].getMainDeck().getItem().getChangeMana();
            }
        }

        for(int i=0;i<5;i++) {
            for(int j=0;j<9;j++) {
                if(getCell(i,j).getFlag()==null&&!getCell(i,j).isBusy()&&getCell(i,j).getItem()==null) {
                    if(random.nextInt(100)>98) {
                        getCell(i,j).setItem(Shop.getItem());
                    }
                }
            }
        }

        boolean finished=false;

        if(mode.equals("hero")){
            if(players[1].getMainDeck().getHero().getLocation()==null){
                players[0].getClient().sendPrint("player " + players[0].getUsername() + " won!");
                players[1].getClient().sendPrint("player " + players[0].getUsername() + " won!");
                players[0].win(award);
                players[0].addMatch(new Match(players[0], players[1],true, turn));
                players[1].addMatch(new Match(players[1], players[0],false, turn));
//                MainMenu.goToMainMenu();
                finished=true;
            }
            if(players[0].getMainDeck().getHero().getLocation()==null){
                players[0].getClient().sendPrint("player " + players[1].getUsername() + " won!");
                players[1].getClient().sendPrint("player " + players[1].getUsername() + " won!");
                players[1].win(award);
                players[1].addMatch(new Match(players[1], players[0],true, turn));
                players[0].addMatch(new Match(players[0], players[1],false, turn));
//                MainMenu.goToMainMenu() ;
                finished=true;
            }
        }
        if(mode.equals("flag6")){
            for(int i=0;i<5;i++){
                for(int j=0;j<9;j++){
                    if(getCell(i,j).isBusy()&&getCell(i,j).getForce().getHadFlag()>=6){
                        if(getCell(i,j).getForce().getOwner()==players[0]){
                            players[0].getClient().sendPrint("player " + players[0].getUsername() + " won!");
                            players[1].getClient().sendPrint("player " + players[0].getUsername() + " won!");
                            players[0].win(award);
                            players[0].addMatch(new Match(players[0], players[1],true, turn));
                            players[1].addMatch(new Match(players[1], players[0],false, turn));
//                            MainMenu.goToMainMenu();
                            finished=true;
                        }
                        else{
                            players[0].getClient().sendPrint("player " + players[1].getUsername() + " won!");
                            players[1].getClient().sendPrint("player " + players[1].getUsername() + " won!");
                            players[1].win(award);
                            players[1].addMatch(new Match(players[1], players[0],true, turn));
                            players[0].addMatch(new Match(players[0], players[1],false, turn));
//                            MainMenu.goToMainMenu() ;
                            finished=true;
                        }
                    }
                }
            }
        }
        if(mode.equals("flag1/2")){
            int flags1=0;
            int flags2=0;
            int half=numberOfFlags/2;
            for(int i=0;i<5;i++){
                for(int j=0;j<9;j++){
                    if(getCell(i,j).isBusy()){
                        if(getCell(i,j).getForce().getOwner()==players[0]){
                            flags1++;
                        }else{
                            flags2++;
                        }
                    }
                }
            }
            if(flags1>half){
                players[0].getClient().sendPrint("player " + players[0].getUsername() + " won!");
                players[1].getClient().sendPrint("player " + players[0].getUsername() + " won!");
                players[0].win(award);
                players[0].addMatch(new Match(players[0], players[1],true, turn));
                players[1].addMatch(new Match(players[1], players[0],false, turn));
//                MainMenu.goToMainMenu();
                finished=true;
            }
            else if(flags2>half){
                players[0].getClient().sendPrint("player " + players[1].getUsername() + " won!");
                players[1].getClient().sendPrint("player " + players[1].getUsername() + " won!");
                players[1].win(award);
                players[1].addMatch(new Match(players[1], players[0],true, turn));
                players[0].addMatch(new Match(players[0], players[1],false, turn));
//                MainMenu.goToMainMenu() ;
                finished=true;
            }
        }

        if(finished){
            getTurnAccount().getClient().sendPrint(showGameInfo());
            getNotTurnAccount().getClient().sendPrint(showGameInfo());
            getTurnAccount().getClient().mainMenu.setCurrentMenu();
            getNotTurnAccount().getClient().mainMenu.setCurrentMenu();
        }else{
            client.sendPrint(showGameInfo());
            getTurnAccount().getClient().sendPrint("\nThis is your turn now");
            getTurnAccount().doYourTurn(this);
        }
    }

    Battle(String mode, int numberOfFlags, Account player1, Account player2){
        this.mode = mode;
        players[0]=player1;
        players[1]=player2;
        this.numberOfFlags=numberOfFlags;
        initialize();

        switch (mode){
            case "flag6":
                getCell(2,4).putFlag(new Flag());
                break;
            case "flag1/2":
                int num=0;
                int k=0;
                for(int i=0;i<5;i++){
                    for(int j=0;j<9;j++){
                        if(k%(9/(numberOfFlags/5.0))==0&&num<numberOfFlags){
                        getCell(i,j).putFlag(new Flag());
                        num++;}
                        k++;
                    }
                }
                break;
        }
    }

    Battle(Account account,String mode, int numberOfFlags, int award, String deck){
        this.mode = mode;
        players[0] = account;

        ClientInfo computerClient = new ComputerClientInfo(deck);
        new PlayMenu(computerClient, this).setCurrentMenu();
        players[1] = computerClient.getLoggedAccount();

        this.numberOfFlags=numberOfFlags;
        this.award=award;
        initialize();

        switch (mode){
            case "flag6":
                getCell(2,4).putFlag(new Flag());
                break;
            case "flag1/2":
                int num=0;
                int k=0;
                for(int i=0;i<5;i++){
                    for(int j=0;j<9;j++){
                        if(k%(9/(numberOfFlags/5.0))==0&&num<numberOfFlags){
                            getCell(i,j).putFlag(new Flag());
                            num++;}
                        k++;
                    }
                }
                break;
        }
    }

    private void initialize() {
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                cells[i][j]=new Cell(i,j);
            }
        }
        players[0].getMainDeck().getHero().setLocation(cells[2][0]);
        players[1].getMainDeck().getHero().setLocation(cells[2][8]);
        players[0].getMainDeck().startGame();
        players[1].getMainDeck().startGame();

        if(getTurnAccount().getMainDeck().getItem()!=null){
            switch (getTurnAccount().getMainDeck().getItem().getBuff()){
                case HOLY:
                case WEAKNESS_NOTDISPEL:
                    useSpell(getTurnAccount().getMainDeck().getItem(),0,0);
                    break;
            }
        }
        if(getNotTurnAccount().getMainDeck().getItem()!=null){
            switch (getNotTurnAccount().getMainDeck().getItem().getBuff()){
                case HOLY:
                case WEAKNESS_NOTDISPEL:
                    useSpell(getNotTurnAccount().getMainDeck().getItem(),0,0);
                    break;
            }
        }

        for(Force force: getMyCardsInGame()) {
            if (force instanceof Minion) {
                if (((Minion) force).getActivateTime() == ActivateTime.ON_TURN ||((Minion) force).getActivateTime() == ActivateTime.PASSIVE) {
                    for (Spell spell : force.getSpecialPower()) {
                        useSpell(spell, force.getLocation().getX(), force.getLocation().getY());
                    }
                }
            }
        }
        for(Force force: getOpponentCardsInGame()) {
            if (force instanceof Minion) {
                if (((Minion) force).getActivateTime() == ActivateTime.ON_TURN ||((Minion) force).getActivateTime() == ActivateTime.PASSIVE) {
                    for (Spell spell : force.getSpecialPower()) {
                        useSpell(spell, force.getLocation().getX(), force.getLocation().getY());
                    }
                }
            }
        }

    }

    private Cell[][] cells = new Cell[5][9];
    private int turn = 1; // odd for player 1's turn and even for player 2's turn

    private Cell getCell(int x, int y) {
        return cells[x][y];
    }

    private ArrayList<Card> getGraveyard() {
        return (getPlayerTurn()==1)?graveyard1:graveyard2;
    }

    private int getIndexTurn(){
        return 1-(turn%2);
    }

    private int getPlayerTurn(){
        return getIndexTurn()+1;
    }

    int getMana() {
        return mana[getIndexTurn()];
    }

    private void useMana(int amount){
        mana[getIndexTurn()]-=amount;
    }

    private void addToGraveyard(Force force){
        if(players[0]== force.getOwner())graveyard1.add(force);
        else graveyard2.add(force);
    }

    Account getTurnAccount(){
        return players[getIndexTurn()];
    }

    Account getNotTurnAccount(){
        return players[1-getIndexTurn()];
    }

    public String showHand(){
        return getTurnAccount().getMainDeck().showHand();
    }

    public String showNextCard(){
        return getTurnAccount().getMainDeck().getNextCard().showCardInfo();
    }

    public String showCardInfo(int id){
        return getTurnAccount().getMainDeck().getCard(id).showCardInfo();
    }

    void attackCombo(ClientInfo client, int opponentId, List<Integer> forcesId){
        Force card = selectedCard;
        for (int id: forcesId){
            Minion force = (Minion) getCardById(getMyCardsInGame(), id);
            if(force == null){
                client.sendPrint("invalid card id");
                return;
            }
            if (force.getActivateTime() != ActivateTime.COMBO) {
                client.sendPrint("card with id " + id + "can't combo attack");
                return;
            }
        }
        boolean first=true;
        for (int id: forcesId){
            setSelectedCard(client, id);
            attack(client, opponentId, first);
            first=false;
        }
        setSelectedCard(client, card.getId());
    }

    ArrayList<Cell> getCells(int x, int y, int area){
        ArrayList<Cell> res=new ArrayList<>();
        for(int i=(x<0?0:x);(i<(x+area))&&(i<5);i++){
            for(int j=(y<0?0:y);(j<(y+area))&&(j<9);j++){
                res.add(cells[i][j]);
            }
        }
        return res;
    }

    private boolean useSpell(Spell spell, int x, int y){
        ArrayList<Force> forces = new ArrayList<>();
        Random rand = new Random();
        ArrayList<Minion> minions=new ArrayList<>();
        ArrayList<Force> forcestmp=new ArrayList<>();
        switch (spell.getTarget()){
            case CELL:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    cell.addEffect(spell);
                }
                return true;
            case FORCE:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()) forces.add(cell.getForce());
                }
                break;
            case ALLY_HERO:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()&&cell.getForce()instanceof Hero&&cell.getForce().getOwner()==getTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case FORCE_ROW:
                for(Cell cell: cells[getTurnAccount().getMainDeck().getHero().getLocation().getX()]){
                    if(cell.isBusy()&&cell.getForce()instanceof Hero&&cell.getForce().getOwner()==getTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case ALLY_FORCE:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()&&cell.getForce().getOwner()==getTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case ALLY_FORCE_MELEE:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()&&cell.getForce().getOwner()==getTurnAccount()&&cell.getForce().getTroopType()==AttackType.MELEE) forces.add(cell.getForce());
                }
                break;
            case ENEMY_HERO:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()&&cell.getForce()instanceof Hero&&cell.getForce().getOwner()==getNotTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case ALLY_MINION:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()&&cell.getForce()instanceof Minion&&cell.getForce().getOwner()==getTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case ENEMY_FORCE:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()&&cell.getForce().getOwner()==getNotTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case ENEMY_MINION:
                for(Cell cell:getCells(x,y,spell.getTargetArea())){
                    if(cell.isBusy()&&cell.getForce()instanceof Minion&&cell.getForce().getOwner()==getNotTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case ENEMY_FORCES_COL:
                for(int i=0; i<5;i++){
                    Cell cell = cells[i][y];
                    if(cell.isBusy()&&cell.getForce().getOwner()==getNotTurnAccount()) forces.add(cell.getForce());
                }
                break;
            case RANDOM_ENEMY_MINION_8:
                if(getTurnAccount().getMainDeck().getHero().getLocation()!=null){
                    for(Cell cell: getAdjacentCells(getTurnAccount().getMainDeck().getHero().getLocation())){
                        if(cell.isBusy()&&cell.getForce() instanceof Minion&&cell.getForce().getOwner()==getNotTurnAccount()){
                            minions.add((Minion) cell.getForce());
                        }
                    }
                    if(minions.size()>0){
                        forces.add(minions.get(rand.nextInt(minions.size())));
                    }
                }
                break;
            case ENEMY_MINION_RANDOM:
                for(Cell cell: getCells(0,0,9)){
                    if(cell.isBusy()&&cell.getForce() instanceof Minion&&cell.getForce().getOwner()==getNotTurnAccount()){
                        minions.add((Minion) cell.getForce());
                    }
                }
                if(minions.size()>0){
                    forces.add(minions.get(rand.nextInt(minions.size())));
                }
                break;
            case ALLY_MINION_RANDOM:
                for(Cell cell: getCells(0,0,9)){
                    if(cell.isBusy()&&cell.getForce() instanceof Minion&&cell.getForce().getOwner()==getTurnAccount()){
                        minions.add((Minion) cell.getForce());
                    }
                }
                if(minions.size()>0){
                    forces.add(minions.get(rand.nextInt(minions.size())));
                }
                break;
            case ALLY_FORCE_RANDOM:
                for(Cell cell: getCells(0,0,9)){
                    if(cell.isBusy()&&cell.getForce().getOwner()==getTurnAccount()){
                        forcestmp.add(cell.getForce());
                    }
                }
                if(forcestmp.size()>0){
                    forces.add(forcestmp.get(rand.nextInt(forcestmp.size())));
                }
                break;
            case ALLY_FORCE_RH_RANDOM:
                for(Cell cell: getCells(0,0,9)){
                    if(cell.isBusy()&&cell.getForce().getOwner()==getTurnAccount()&&cell.getForce().getTroopType()!=AttackType.MELEE){
                        forcestmp.add(cell.getForce());
                    }
                }
                if(forcestmp.size()>0){
                    forces.add(forcestmp.get(rand.nextInt(forcestmp.size())));
                }
                break;
        }
        for(Force force: forces){
            force.addEffect(spell);
            checkIfDead(force);
        }
        return forces.size()>0;
    }

    void useSpecialPower(ClientInfo client, int x, int y){
        if(getTurnAccount().getMainDeck().getHero().getManaCost()>getMana()){
            client.sendPrint("You don't have enough mana");
            return;
        }
        if(getTurnAccount().getMainDeck().getHero().getLocation()==null){
            client.sendPrint("your hero is dead");
            return;
        }
        if(getTurnAccount().getMainDeck().getHero().isCooling()){
            client.sendPrint("your hero is cooling down");
            return;
        }
        if(getTurnAccount().getMainDeck().getHero().getSpecialPower().size()==0){
            client.sendPrint("you don't have any special powers");
            return;
        }
        boolean used=false;
        for(Spell spell: getTurnAccount().getMainDeck().getHero().getSpecialPower()) {
            if(useSpell(spell, x,y)){
                used = true;
            }
        }
        if(used){
            useMana(getTurnAccount().getMainDeck().getHero().getManaCost());
            getTurnAccount().getMainDeck().getHero().useSpecialPower();
            client.sendPrint("special power used");
        }else{
            client.sendPrint("Invalid target");
        }
    }

    private void checkIfDead(Force force){
        if(force.getHealth() <= 0){
            if(force instanceof Minion){
                if(((Minion) force).getActivateTime()==ActivateTime.ON_DEATH){
                    for(Spell spell: force.getSpecialPower()){
                        useSpell(spell, force.getLocation().getX()-spell.getTargetArea()/2, force.getLocation().getY()-spell.getTargetArea()/2);
                    }
                }
            }
            if(getTurnAccount().getMainDeck().getItem()!=null && getTurnAccount().getMainDeck().getItem().getBuff()==Buff.POWER_ON_DEATH){
                useSpell(getTurnAccount().getMainDeck().getItem(), 0, 0);
            }
            if(force.getFlag()!=null){
                force.getLocation().putFlag(force.getFlag());
                force.loseFlag();
            }
            force.die();
            addToGraveyard(force);
            if(selectedCard == force){
                selectedCard = null;
            }
        }
    }

    void attack(ClientInfo client, int id, boolean defend){
        Force target = (Force) getCardById(getOpponentCardsInGame(), id);
        if(target==null){
            client.sendPrint("Invalid card id");
            return;
        }
        if(selectedCard==null){
            client.sendPrint("please select a force");
            return;
        }
        Force force = selectedCard;
        if(force instanceof Minion){
            if(((Minion) force).getActivateTime()==ActivateTime.ON_ATTACK){
                for(Spell spell: force.getSpecialPower()){
                    useSpell(spell, target.getLocation().getX(), target.getLocation().getY());
                }
            }
        }
        if(selectedCard instanceof Hero
                && getTurnAccount().getMainDeck().getItem()!=null
                && (getTurnAccount().getMainDeck().getItem().getBuff()==Buff.DISARM_RH_ON_ATTACK
                && (target.getTroopType()==AttackType.HYBRID||target.getTroopType()==AttackType.RANGED) || getTurnAccount().getMainDeck().getItem().getBuff()==Buff.DISARM_ON_ATTACK)
                ){
            target.addEffect(getTurnAccount().getMainDeck().getItem());
        }
        if(getTurnAccount().getMainDeck().getItem()!=null
                && getTurnAccount().getMainDeck().getItem().getBuff()==Buff.WEAKNESS_ON_ATTACK){
            target.addEffect(getTurnAccount().getMainDeck().getItem());
        }
        selectedCard.attack(target, turn, defend);
        if(defend && target.canDefend() && target.getTroopType().isInRangeForDefend(target, selectedCard)){
            if(target instanceof Minion){
                if(((Minion) target).getActivateTime()==ActivateTime.ON_DEFEND){
                    for(Spell spell: target.getSpecialPower()){
                        useSpell(spell, target.getLocation().getX(), target.getLocation().getY());
                    }
                }
            }
        }
        checkIfDead(selectedCard);
        checkIfDead(target);
    }

    public void insert(ClientInfo client, String cardName, int x, int y){
        Card card = getTurnAccount().getMainDeck().getCardFromHand(cardName, false);
        if(card==null){
            client.sendPrint("Invalid card name");
            return;
        }
        Cell cell = getCell(x, y);
        if(cell == null || card instanceof Minion && !isAdjacentToFriendCell(cell)){
            client.sendPrint("Invalid target");
            return;
        }
        if(card.getManaCost()>getMana()){
            client.sendPrint("You don't have enough mana");
            return;
        }
        getTurnAccount().getMainDeck().getCardFromHand(cardName, true);
        useMana(card.getManaCost());
        if(card instanceof Minion){
            card.setLocation(cell);
            Force force= (Force) card;
            if(((Minion) force).getActivateTime()==ActivateTime.ON_SPAWN){
                for(Spell spell: force.getSpecialPower()){
                    useSpell(spell, force.getLocation().getX()-spell.getTargetArea()/2, force.getLocation().getY()-spell.getTargetArea()/2);
                }
            }
            if(getTurnAccount().getMainDeck().getItem()!=null
                    && getTurnAccount().getMainDeck().getItem().getBuff()==Buff.ATTACK_ON_INSERT){
                useSpell(getTurnAccount().getMainDeck().getItem(), 0,0);
            }
            if(getTurnAccount().getMainDeck().getItem()!=null
                    && getTurnAccount().getMainDeck().getItem().getBuff()==Buff.HOLY_ON_SPAWN){
                useSpell(getTurnAccount().getMainDeck().getItem(), 0,0);
            }
        }else{
            useSpell((Spell) card, x, y);
        }
        client.sendPrint("card inserted");
    }

    public void move(ClientInfo client, int x, int y) {
        Cell cell = getCell(x,y);
        if(selectedCard==null){
            client.sendPrint("please select a force");
            return;
        }
        if(cell == null || cell.isBusy() || interruption(selectedCard.getLocation() , cell)){
            client.sendPrint("Invalid target");
            return;
        }
        selectedCard.move(getCell(x, y), turn);
    }

    public void setSelectedCard(ClientInfo client, int id) {
        Force force = (Force) getCardById(getMyCardsInGame(),id);
        if(force==null){
            client.sendPrint("Invalid card id: "+id);
            return;
        }
        selectedCard = force;
        client.sendPrint("card selected");
    }

    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private void helper(int start, int[] nums, List<List<Integer>> result){
        if(start==nums.length-1){
            ArrayList<Integer> list = new ArrayList<>();
            for(int num: nums){
                list.add(num);
            }
            result.add(list);
            return;
        }
        for(int i=start; i<nums.length; i++){
            swap(nums, i, start);
            helper(start+1, nums, result);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private boolean interruption(Cell cell1, Cell cell2){
        int x1=cell1.getX(), y1=cell1.getY(), x2=cell2.getX(), y2=cell2.getY();
        int nums[] = new int[cell1.getDistance(cell2)];
        int i = 0;
        if(x2>x1){
            for(;i<x2-x1;i++){
                nums[i] = 1;
            }
        }else{
            for(;i<x1-x2;i++){
                nums[i] = -1;
            }
        }
        int j = i;
        if(y2>y1){
            for(;j-i<y2-y1;j++){
                nums[j]=2;
            }
        }else{
            for(;j-i<y1-y2;j++){
                nums[j]=-2;
            }
        }
        List<List<Integer>> routes = permute(nums);
        for(List<Integer> route: routes){
            boolean interruption = false;
            int right = x1;
            int up = y1;
            for(int dir: route){
                switch (dir){
                    case 1:
                    case -1:
                        right += dir;
                        break;
                    case 2:
                    case -2:
                        up += dir/2;
                        break;
                }
                if(getCell(right,up).isBusy() && getCell(right, up).getForce().getOwner()==getNotTurnAccount()){
                    interruption=true;
                }
            }
            if(!interruption) return false;
        }
        return true;
    }

    public String showGraveyard(){
        StringBuilder res = new StringBuilder("");
        for (Card card : getGraveyard()) {
            res.append(card.showCardInfo()).append("\n");
        }
        return res.toString();
    }

    public String showGraveyardCard(int id){
        return getTurnAccount().getMainDeck().getCard(id).showCardInfo();
    }

    private boolean isAdjacentToFriendCell(Cell cell) {
        int x=cell.getX(), y=cell.getY();
        if(x>0){
            if(y>0) if(getCell(x-1,y-1).isBusy()&&getCell(x-1,y-1).getForce().getOwner()==getTurnAccount()) return true;
            if(getCell(x-1,y).isBusy()&&getCell(x-1,y).getForce().getOwner()==getTurnAccount()) return true;
            if(y<8) if(getCell(x-1,y+1).isBusy()&&getCell(x-1,y+1).getForce().getOwner()==getTurnAccount()) return true;
        }
        if(y>0) if(getCell(x,y-1).isBusy()&&getCell(x,y-1).getForce().getOwner()==getTurnAccount()) return true;
        if(y<8) if(getCell(x,y+1).isBusy()&&getCell(x,y+1).getForce().getOwner()==getTurnAccount()) return true;
        if(x<4){
            if(y>0) if(getCell(x+1,y-1).isBusy()&&getCell(x+1,y-1).getForce().getOwner()==getTurnAccount()) return true;
            if(getCell(x+1,y).isBusy()&&getCell(x+1,y).getForce().getOwner()==getTurnAccount()) return true;
            if(y<8)
                return getCell(x + 1, y + 1).isBusy() && getCell(x + 1, y + 1).getForce().getOwner() == getTurnAccount();
        }
        return false;
    }
    ArrayList<Cell> getAdjacentCells(Cell cell) {
        ArrayList<Cell> res=new ArrayList<>();
        int x=cell.getX(), y=cell.getY();
        if(x>0){
            if(y>0) res.add(getCell(x-1,y-1));
            res.add(getCell(x-1,y));
            if(y<8) res.add(getCell(x-1,y+1));
        }
        if(y>0) res.add(getCell(x,y-1));
        if(y<8) res.add(getCell(x,y+1));
        if(x<4){
            if(y>0) res.add(getCell(x+1,y-1));
            res.add(getCell(x+1,y));
            if(y<8) res.add(getCell(x + 1, y + 1));
        }
        return res;
    }

    public ArrayList<Force> getMyCardsInGame(){
        ArrayList<Force> cards = new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(getCell(i,j).isBusy()){
                    if(getCell(i,j).getForce().getOwner()==getTurnAccount()){
                        cards.add(getCell(i,j).getForce());
                    }
                }
            }
        }
        return cards;
    }

    public ArrayList<Force> getOpponentCardsInGame(){
        ArrayList<Force> cards = new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(getCell(i,j).isBusy()){
                    if(getCell(i,j).getForce().getOwner()==getNotTurnAccount()){
                        cards.add(getCell(i,j).getForce());
                    }
                }
            }
        }
        return cards;
    }

    private Card getCardById(ArrayList<Force> cards, int id){
        for(Card card: cards){
            if(card.getId()==id) return card;
        }
        return null;
    }

    public int getTurn() {
        return turn;
    }
}

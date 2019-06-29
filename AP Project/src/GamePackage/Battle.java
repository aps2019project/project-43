package GamePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

    private boolean multiPlayer; // false for single player and true for multi player
    private boolean Story; // false for custom game and true for story
    private String mode; //
    private Account[] players = new Account[2];
    private int[] mana = new int[]{2,2};
    private Force selectedCard;
    private ArrayList<Card> graveyard1 =new ArrayList<>();
    private ArrayList<Card> graveyard2 =new ArrayList<>();

    public void showBoard(){
        System.out.println();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(!cells[i][j].isBusy()) System.out.print("O  ");
                else if(cells[i][j].getForce() instanceof Hero){
                    if(cells[i][j].getForce().getOwner()==players[0]) System.out.print("H1 ");
                    if(cells[i][j].getForce().getOwner()==players[1]) System.out.print("H2 ");
                }
                else if(cells[i][j].getForce() instanceof Minion){
                    if(cells[i][j].getForce().getOwner()==players[0]) System.out.print("M1 ");
                    if(cells[i][j].getForce().getOwner()==players[1]) System.out.print("M2 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showGameInfo(){
        System.out.println("player1 mana: " + mana[0]);
        System.out.println("player2 mana: " + mana[1]);
        switch (mode) {
            case "hero":
                System.out.println("player0 hero health: " + players[0].getMainDeck().getHero().getHealth());
                System.out.println("player1 hero health: " + players[1].getMainDeck().getHero().getHealth());
                break;
            case "flag6":
                //todo
                break;
            case "flag1/2":
                //todo
                break;
        }
    }

    void endTurn(){
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
        turn++;
        mana[0] = mana[1] = turn / 2 + 2;
        if (turn > 14) mana[0] = mana[1] = 9;


        for(Force force: getMyCardsInGame()) {
            if (force instanceof Minion) {
                if (((Minion) force).getActivateTime() == ActivateTime.ON_TURN ||((Minion) force).getActivateTime() == ActivateTime.PASSIVE) {
                    for (Spell spell : force.getSpecialPower()) {
                        useSpell(spell, force.getLocation().getX(), force.getLocation().getY());
                    }
                }
            }
        }


        if(mode.equals("hero")){
            if(players[0].getMainDeck().getHero().getAp()<=0){
                System.out.println("player " + players[0].getUsername() + " won!");
                players[0].win(1000);
                players[0].addMatch(new Match(players[1],true, turn));
                players[1].addMatch(new Match(players[0],false, turn));
                MainMenu.goToMainMenu();
            }
            if(players[1].getMainDeck().getHero().getAp()<=0){
                System.out.println("player " + players[1].getUsername() + " won!");
                players[1].win(1000);
                players[1].addMatch(new Match(players[0],true, turn));
                players[0].addMatch(new Match(players[1],false, turn));
                MainMenu.goToMainMenu() ;
            }
        }
        if(mode.equals("flag7")){
            //todo
        }
        if(mode.equals("flag1/2")){
            //todo
        }
    }

    Battle(String mode, Account player1, Account player2){
        multiPlayer = true;
        this.mode = mode;
        players[0]=player1;
        players[1]=player2;
        initialize();
    }

    Battle(String mode){
        multiPlayer = false;
        this.mode = mode;
        players[0] = Account.getLoggedAccount();
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

    private int getMana() {
        return mana[getIndexTurn()];
    }

    private void useMana(int amount){
        mana[getIndexTurn()]-=amount;
    }

    private void addToGraveyard(Force force){
        if(players[0]== force.getOwner())graveyard1.add(force);
        else graveyard2.add(force);
    }

    private Account getTurnAccount(){
        return players[getIndexTurn()];
    }

    private Account getNotTurnAccount(){
        return players[1-getIndexTurn()];
    }

    public void showHand(){
        getTurnAccount().getMainDeck().showHand();
    }

    public void showNextCard(){
        getTurnAccount().getMainDeck().getNextCard().showCardInfo();
    }

    public void showCardInfo(int id){
        getTurnAccount().getMainDeck().getCard(id).showCardInfo();
    }

    void attackCombo(int opponentId, List<Integer> forcesId){
        Force card = selectedCard;
        for (int id: forcesId){
            Minion force = (Minion) getCardById(getMyCardsInGame(), id);
            if(force == null){
                System.out.println("invalid card id");
                return;
            }
            if (force.getActivateTime() != ActivateTime.COMBO) {
                System.out.println("card with id " + id + "can't combo attack");
                return;
            }
        }
        boolean first=true;
        for (int id: forcesId){
            setSelectedCard(id);
            attack(opponentId, first);
            first=false;
        }
        setSelectedCard(card.getId());
    }

    private ArrayList<Cell> getCells(int x,int y,int area){
        ArrayList<Cell> res=new ArrayList<>();
        for(int i=x;i<x+area&&i<5;i++){
            for(int j=y;j<y+area&&y<9;y++){
                res.add(cells[i][j]);
            }
        }
        return res;
    }

    private boolean useSpell(Spell spell, int x, int y){
        ArrayList<Force> forces = new ArrayList<>();
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
            case RANDOM_ENEMY_MINION:
                if(getTurnAccount().getMainDeck().getHero().getLocation()!=null){
                    Random rand = new Random();
                    ArrayList<Minion> minions=new ArrayList<>();
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
        }
        for(Force force: forces){
            force.addEffect(spell);
            checkIfDead(force);
        }
        return forces.size()>0;
    }

    void useSpecialPower(int x, int y){
        if(getTurnAccount().getMainDeck().getHero().getManaCost()>getMana()){
            System.out.println("You don't have enough mana");
            return;
        }
        if(getTurnAccount().getMainDeck().getHero().getLocation()==null){
            System.out.println("your hero is dead");
            return;
        }
        if(getTurnAccount().getMainDeck().getHero().isCooling()){
            System.out.println("your hero is cooling down");
            return;
        }
        if(getTurnAccount().getMainDeck().getHero().getSpecialPower().size()==0){
            System.out.println("you don't have any special powers");
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
        }else{
            System.out.println("Invalid target");
        }
    }

    private void checkIfDead(Force force){
        if(force.getHealth() <= 0){
            if(force instanceof Minion){
                if(((Minion) force).getActivateTime()==ActivateTime.ON_DEATH){
                    for(Spell spell: force.getSpecialPower()){
                        useSpell(spell, force.getLocation().getX(), force.getLocation().getY());
                    }
                }
            }
            force.die();
            addToGraveyard(force);
            if(selectedCard == force){
                selectedCard = null;
            }
        }
    }

    void attack(int id, boolean defend){
        Force target = (Force) getCardById(getOpponentCardsInGame(), id);
        if(target==null){
            System.out.println("Invalid card id");
            return;
        }
        Force force = selectedCard;
        if(force instanceof Minion){
            if(((Minion) force).getActivateTime()==ActivateTime.ON_ATTACK || ((Minion) force).getActivateTime()==ActivateTime.ON_SPAWN){
                for(Spell spell: force.getSpecialPower()){
                    useSpell(spell, force.getLocation().getX(), force.getLocation().getY());
                }
            }
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

    public void insert(String cardName, int x, int y){
        Card card = getTurnAccount().getMainDeck().getCardFromHand(cardName, false);
        if(card==null){
            System.out.println("Invalid card name");
            return;
        }
        Cell cell = getCell(x, y);
        if(cell == null || card instanceof Minion && !isAdjacentToFriendCell(cell)){
            System.out.println("Invalid target");
            return;
        }
        if(card.getManaCost()>getMana()){
            System.out.println("You don't have enough mana");
            return;
        }
        getTurnAccount().getMainDeck().getCardFromHand(cardName, true);
        useMana(card.getManaCost());
        if(card instanceof Minion){
            card.setLocation(cell);
        }else{
            useSpell((Spell) card, x, y);
        }
    }

    public void move(int x, int y) {
        Cell cell = getCell(x,y);
        if(cell == null || cell.isBusy() || interruption(selectedCard.getLocation() , cell)){
            System.out.println("Invalid target");
            return;
        }
        selectedCard.move(getCell(x, y), turn);
        Force force = selectedCard;
        if(force instanceof Minion){
            if(((Minion) force).getActivateTime()==ActivateTime.ON_SPAWN){
                for(Spell spell: force.getSpecialPower()){
                    useSpell(spell, force.getLocation().getX(), force.getLocation().getY());
                }
            }
        }
    }

    public void setSelectedCard(int id) {
        Force force = (Force) getCardById(getMyCardsInGame(),id);
        if(force==null){
            System.out.println("Invalid card id: "+id);
            return;
        }
        selectedCard = force;
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

    public void showGraveyard(){
        for (Card card : getGraveyard()) {
            card.showCardInfo();
            System.out.println();
        }
    }

    public void showGraveyardCard(int id){
        getTurnAccount().getMainDeck().getCard(id).showCardInfo();
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
        if(x<5){
            if(y>0) if(getCell(x+1,y-1).isBusy()&&getCell(x+1,y-1).getForce().getOwner()==getTurnAccount()) return true;
            if(getCell(x+1,y).isBusy()&&getCell(x+1,y).getForce().getOwner()==getTurnAccount()) return true;
            if(y<8)
                return getCell(x + 1, y + 1).isBusy() && getCell(x + 1, y + 1).getForce().getOwner() == getTurnAccount();
        }
        return false;
    }
    private ArrayList<Cell> getAdjacentCells(Cell cell) {
        ArrayList<Cell> res=new ArrayList<>();
        int x=cell.getX(), y=cell.getY();
        if(x>0){
            if(y>0) res.add(getCell(x-1,y-1));
            res.add(getCell(x-1,y));
            if(y<8) res.add(getCell(x-1,y+1));
        }
        if(y>0) res.add(getCell(x,y-1));
        if(y<8) res.add(getCell(x,y+1));
        if(x<5){
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

}

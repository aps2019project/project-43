package GamePackage;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Battle extends GameMenu {

    private static GameMenu battleCommands = new Battle();
    private static boolean multiPlayer; // false for single player and true for multi player
    private boolean Story; // false for custom game and true for story
    private static String mode; //
    private static Account[] players = new Account[2];
    private int[] mana = new int[]{2,2};
    private String[] inputArray;
    private Attacker selectedCard;
    private ArrayList<Card> graveyard1 =new ArrayList<>();
    private ArrayList<Card> graveyard2 =new ArrayList<>();
    private boolean inGraveyard = false;

    public Cell[][] cells = new Cell[5][9];
    private int turn = 1; // odd for player 1's turn and even for player 2's turn

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public ArrayList<Card> getGraveyard() {
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

    public void addToGraveyard(Attacker attacker){
        if(players[0]==attacker.getOwner())graveyard1.add(attacker);
        else graveyard2.add(attacker);
    }

    private Account getTurnAccount(){
        return players[getIndexTurn()];
    }

    private Account getNotTurnAccount(){
        return players[1-getIndexTurn()];
    }

    private Battle(){}

    public static void setSecondPlayer(Account user2) {
        players[1] = user2;
    }

    public static void setFirstPlayer(Account user1) {
        players[0] = user1;
    }

    public static void setMultiPlayer(boolean multiPlayer) {
        Battle.multiPlayer = multiPlayer;
    }

    public static void setMode(String mode) {
        Battle.mode = mode;
    }

    @Override
    public void setState(String input) {
        try {
            input = input.trim().toLowerCase();
            inputArray = input.split(" ");
            if (!inGraveyard)
                switch (inputArray[0]) {
                    case "game":
                        System.out.println("player1 mana: " + mana[0]);
                        System.out.println("player2 mana: " + mana[1]);
                        switch (mode) {
                            case "hero":
                                System.out.println("player0 hero health: " + players[0].getMainDeck().getHero().getHp());
                                System.out.println("player1 hero health: " + players[1].getMainDeck().getHero().getHp());
                                break;
                            case "flag6":
                                //To-Do
                                break;
                            case "flag1/2":
                                //To-Do
                                break;
                        }
                        break;
                    case "move":
                        try {
                            move(parseInt(inputArray[2].substring(2, inputArray[2].length() - 2)), parseInt(inputArray[3].substring(1, inputArray[3].length() - 2)));
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Sub String Related Error");
                        }
                        break;
                    case "select":
                        setSelectedCard(parseInt(inputArray[1]));
                        break;
                    case "show":
                        switch (inputArray[1]) {
                            case "my":
                                printMinions(getMyCardsInGame());
                                break;
                            case "opponent":
                                printMinions(getOpponentCardsInGame());
                                break;
                            case "card":
                                showCardInfo(getCardInDeckById(parseInt(inputArray[3])));
                                break;
                            case "hand":
                                showHand();
                                break;
                            case "next":
                                showCardInfo(getTurnAccount().getMainDeck().getNextCard());
                                break;
                        }
                        break;
                    case "insert":
                        insert(inputArray[1], parseInt(inputArray[3].substring(1, inputArray[3].length() - 1)), parseInt(inputArray[4].substring(0, inputArray[4].length() - 1)));
                        break;
                    case "enter":
                        inGraveyard = true;
                        break;
                    case "end":
                        getTurnAccount().getMainDeck().addNextCard();
                        turn++;
                        mana[0] = mana[1] = turn / 2 + 2;
                        if (turn > 14) mana[0] = mana[1] = 9;

                        if(players[0].getMainDeck().getHero().getAp()<=0){
                            System.out.println("player " + players[0].getUsername() + " won!");
                            players[0].addMoney(1000);
                            players[0].addWins();
                            players[0].getMatches().add(new Match(new Account[]{players[0],players[1]}, true, turn));
                            MainMenu.goToMainMenu();
                        }
                        if(players[1].getMainDeck().getHero().getAp()<=0){
                            System.out.println("player " + players[1].getUsername() + " won!");
                            players[1].addMoney(1000);
                            players[1].addWins();
                            players[1].getMatches().add(new Match(new Account[]{players[0],players[1]}, false, turn));
                            MainMenu.goToMainMenu();
                        }
                        break;
                    case "attack":
                        switch (inputArray[1]) {
                            case "combo":
                                ArrayList<Attacker> cards = new ArrayList<>();
                                for (int i = 3; i < inputArray.length; i++) {
                                    cards.add((Attacker) getCardById(getMyCardsInGame(), parseInt(inputArray[i])));
                                }
                                attackCombo((Attacker) getCardById(getMyCardsInGame(), parseInt(inputArray[2])), cards);
                                break;
                            default:
                                attack(parseInt(inputArray[1]));
                        }
                        break;
                    default: {
                        System.out.println("Invalid Command");
                    }
                }
            else switch (inputArray[0]) {
                case "show":
                    switch (inputArray[1]) {
                        case "cards":
                            for (Card card : getGraveyard()) {
                                showCardInfo(card);
                                System.out.println();
                            }
                            break;
                        case "info":
                            for (Card card : getTurnAccount().getMainDeck().getCards()) {
                                if (card.getCardID() == parseInt(inputArray[2])) showCardInfo(card);
                            }
                            break;
                    }
                    break;
                case "exit":
                    inGraveyard = false;
                    break;
                default: {
                    System.out.println("Invalid Command");
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void attackCombo(Attacker opponent, ArrayList<Attacker> cards) {
        //todo
    }

    private void attack(int id){
        Attacker target = (Attacker) getCardById(getOpponentCardsInGame(), id);
        if(target==null){
            System.out.println("Invalid card id");
            return;
        }
        if(selectedCard.troopType.isInRangeForAttack(selectedCard, target)){
            System.out.println("opponent minion is unavailable for attack");
            return;
        }
        if(selectedCard.turnAttacked == turn || selectedCard.isStunned()){
            System.out.println("Card with id "+ id +" can't attack");
            return;
        }
        selectedCard.attack(turn);
        target.getHit(selectedCard.ap);
        if(!target.isDisarmed() && target.troopType.isInRangeForDefend(target, selectedCard)){
            selectedCard.getHit(target.ap);
        }
        if(selectedCard.ap <=0) {
            selectedCard.die();
            addToGraveyard(selectedCard);
        }
        if(target.ap <= 0){
            target.die();
            addToGraveyard(target);
        }
    }

    private void insert(String cardName, int x, int y){
        Card card = getTurnAccount().getMainDeck().getCardFromHand(cardName, false);
        if(card==null){
            System.out.println("Invalid card name");
            return;
        }
        //todo put some of them in anywhere && check target for spell
        if(card instanceof Minion && !adjacentToFriendCell(x,y)){
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
            card.setCardLocation(getCell(x, y));
            getCell(x, y).setBusy(true);
            getCell(x, y).force = (Attacker) card;
        }
    }

    private void move(int x, int y) {
        // todo if a card can move more than two cells
        if(getDistance(selectedCard.getCardLocation().getX(), selectedCard.getCardLocation().getY(),x, y) > 2
                ||getCell(x, y).isBusy()
                ||interruption(selectedCard.getCardLocation().getX(), selectedCard.getCardLocation().getY(),x, y)
                ||selectedCard.turnMoved==turn
                ||selectedCard.isStunned()){
            System.out.println("Invalid target");
            return;
        }
        selectedCard.getCardLocation().setBusy(false);
        selectedCard.getCardLocation().force=null;
        selectedCard.setCardLocation(getCell(x, y));
        getCell(x, y).setBusy(true);
        getCell(x, y).force = selectedCard;
        selectedCard.turnMoved=turn;
    }

    private void printMinions(ArrayList<Card> minions) {
        for (Card minion: minions) {
            if(minion instanceof Attacker)
            System.out.println(minion.getCardID() + " : " + minion.getName() +
                    "health : " + ((Attacker) minion).getHp() + ", location : (" + minion.getCardLocation().getX() +
                    ", " + minion.getCardLocation().getY() + "), power : " + ((Attacker) minion).getAp());
        }
    }

    private void setSelectedCard(int id) {
        boolean findIt = false;
        if (players[0].getMainDeck().getHero().getCardID() == id) {
            selectedCard = players[getIndexTurn()].getMainDeck().getHero();
            findIt = true;
        } else {
            for(int i=0;i<5;i++){
                for(int j=0;j<9;j++){
                    if(cells[i][j].force !=null&&cells[i][j].force.getOwner()==players[getIndexTurn()]){
                        selectedCard = cells[i][j].force;
                        findIt=true;
                        break;
                    }
                }
            }
        }
        if(!findIt){
            System.out.println("Invalid card id");
        }
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return ((x2>x1)?(x2-x1):(x1-x2)) + ((y2>y1)?(y2-y1):(y1-y2));
    }
    private int getDistance(Cell cell1, Cell cell2) {
        int x1=cell1.getX(), x2=cell2.getX(), y1=cell1.getY(), y2=cell2.getY();
        return ((x2>x1)?(x2-x1):(x1-x2)) + ((y2>y1)?(y2-y1):(y1-y2));
    }

    private Card getCardInDeckById(int id){
        if(getTurnAccount().getMainDeck().getHero()!=null&& getTurnAccount().getMainDeck().getHero().getCardID()==id){
            return getTurnAccount().getMainDeck().getHero();
        }
        for(Card card: getTurnAccount().getMainDeck().getCards()){
            if(card.getCardID()==id) return card;
        }
        return null;
    }

    public List<List<Integer>> permute(int[] nums) {
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

    private boolean interruption(int x1, int y1, int x2, int y2){
        int nums[] = new int[getDistance(x1, y1,x2,y2)];
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
                if(getCell(right,up).isBusy() && getCell(right, up).force.getOwner()==getNotTurnAccount()){
                    interruption=true;
                }
            }
            if(!interruption) return false;
        }
        return true;
    }

    private void showCardInfo(Card card) {
        if(card == null){
            System.out.println("no card");
        }
        if(card instanceof Hero){
            System.out.println("Hero:");
            System.out.println("Name: "+card.getName());
            System.out.println("Cost: "+card.getMoneyCost());
            System.out.println("Desc: "+card.description);
        }
        if(card instanceof Minion){
            System.out.println("Minion:");
            System.out.println("Name: "+card.getName());
            System.out.println("HP: "+((Minion) card).getHp() + " AP: "+((Minion) card).getAp()+" MP: "+card.getManaCost());
            System.out.println("combo-ability: ");
            System.out.println("Cost: "+card.getMoneyCost());
            System.out.println("Desc: "+card.description);
        }
        if(card instanceof Spell){
            System.out.println("Minion:");
            System.out.println("Name: "+card.getName());
            System.out.println("MP: "+card.getManaCost());
            System.out.println("Cost: "+card.getMoneyCost());
            System.out.println("Desc: "+card.description);
        }
    }

    private void showHand(){
        for(Card card: getTurnAccount().getMainDeck().hand){
            showCardInfo(card);
            System.out.println();
        }
        System.out.println("Next Card:");
        showCardInfo(getTurnAccount().getMainDeck().getNextCard());
        System.out.println();
    }



    private boolean adjacentToFriendCell(int x, int y){
        if(x>0){
            if(y>0) if(getCell(x-1,y-1).isBusy()&&getCell(x-1,y-1).force.getOwner()==getTurnAccount()) return true;
            if(getCell(x-1,y).isBusy()&&getCell(x-1,y).force.getOwner()==getTurnAccount()) return true;
            if(y<8) if(getCell(x-1,y+1).isBusy()&&getCell(x-1,y+1).force.getOwner()==getTurnAccount()) return true;
        }
        if(y>0) if(getCell(x,y-1).isBusy()&&getCell(x,y-1).force.getOwner()==getTurnAccount()) return true;
        if(y<8) if(getCell(x,y+1).isBusy()&&getCell(x,y+1).force.getOwner()==getTurnAccount()) return true;
        if(x<5){
            if(y>0) if(getCell(x+1,y-1).isBusy()&&getCell(x+1,y-1).force.getOwner()==getTurnAccount()) return true;
            if(getCell(x+1,y).isBusy()&&getCell(x+1,y).force.getOwner()==getTurnAccount()) return true;
            if(y<8) if(getCell(x+1,y+1).isBusy()&& getCell(x+1,y+1).force.getOwner()==getTurnAccount()) return true;
        }
        return false;
    }

    private ArrayList<Card> getMyCardsInGame(){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(getCell(i,j).isBusy()){
                    if(getCell(i,j).force.getOwner()==getTurnAccount()){
                        cards.add(getCell(i,j).force);
                    }
                }
            }
        }
        return cards;
    }

    private ArrayList<Card> getOpponentCardsInGame(){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                if(getCell(i,j).isBusy()){
                    if(getCell(i,j).force.getOwner()==getNotTurnAccount()){
                        cards.add(getCell(i,j).force);
                    }
                }
            }
        }
        return cards;
    }

    private Card getCardByName(ArrayList<Card> cards, String name){
        for(Card card: cards){
            if(card.getName().equalsIgnoreCase(name)) return card;
        }
        return null;
    }

    private Card getCardById(ArrayList<Card> cards, int id){
        for(Card card: cards){
            if(card.getCardID()==id) return card;
        }
        return null;
    }

    private void showMenu() {
        System.out.print("1. Game info\n" +
                "2. Show my minions\n" +
                "3. Show opponent minions\n" +
                "4. Show card info [card id]\n" +
                "5. Select [card id]\n" +
                "6. Move to ([x], [y])\n" +
                "7. Attack [opponent card id]\n" +
                "8. Attack combo [opponent card id] [my card id] [my card id] [...]\n" +
                "9. Use special power (x, y)\n" +
                "10. Show hand\n" +
                "11. Insert [card name] in (x, y)\n" +
                "12. End turn\n" +
                "13. Show collectables\n" +
                "14. Select [collectable id]" +
                "15. Show info\n" +
                "16. Use [location x, y]\n" +
                "17. Show next card\n" +
                "18. Enter graveyard\n" +
                "19. Show cards\n" +
                "20. End game\n" +
                "21. Show menu\n" +
                "22. Help\n" +
                "23. Exit\n");
    }

    public static void startBattle () {
        battleCommands = new Battle();
        for(int i=0;i<5;i++){
            for(int j=0;j<9;j++){
                ((Battle)battleCommands).cells[i][j]=new Cell();
            }
        }
        players[0].getMainDeck().getHero().setCardLocation(((Battle)battleCommands).cells[2][0]);
        players[1].getMainDeck().getHero().setCardLocation(((Battle)battleCommands).cells[2][8]);
        ((Battle)battleCommands).cells[2][0].setBusy(true);
        ((Battle)battleCommands).cells[2][8].setBusy(true);
        ((Battle)battleCommands).cells[2][0].force=players[0].getMainDeck().getHero();
        ((Battle)battleCommands).cells[2][8].force=players[1].getMainDeck().getHero();
        players[0].getMainDeck().startGame();
        players[1].getMainDeck().startGame();
        GameMenu.setCurrentMenu(battleCommands);
        ((Battle) battleCommands).showMenu();
    }

}

package GamePackage;

public class BattleMenu extends GameMenu {

    private State state=State.INITIAL;
    private Account opponent;
    private String deck;

    BattleMenu(ClientInfo client) {
        super(client);
    }

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        switch (state){
            case INITIAL:
                switch (input){
                    case "single player":
                        showSinglePlayerMenu();
                        state=State.SINGLE;
                        break;
                    case "multi player":
                        client.server.printOnlineUsers(client);
                        state=State.USER;
                        break;
                    case "help":
                        showMenu();
                        break;
                    case "exit":
                        client.mainMenu.setCurrentMenu();
                        break;
                    default:
                        client.sendPrint("Invalid Command\n");
                        showMenu();
                }
                break;
            case USER:
                opponent = client.server.getOnlineUser(input);
                if (opponent==null || opponent.getUsername().equals(client.getLoggedAccount().getUsername())){
                    client.sendPrint("username is not valid!");
                    state=State.INITIAL;
                    break;
                }
                state=State.MODE;
                if(client.hasPlayRequest(opponent.getClient())) {
                    client.acceptPlayRequest(opponent.getClient());
                } else showModeMenu();
                break;
            case MODE:
                selectMode(input);
                break;
            case SINGLE:
                switch (input){
                    case "story":
                        showStoryMenu();
                        state=State.STORY;
                        break;
                    case "custom game":
                        showCustomMenu();
                        state=State.CUSTOM;
                        break;
                    case "exit":
                        showMenu();
                        state=State.INITIAL;
                        break;
                    case "help":
                        showSinglePlayerMenu();
                        break;
                    default:
                        client.sendPrint("Invalid Command\n");
                        showSinglePlayerMenu();
                }
                break;
            case STORY:
                switch (input) {
                    case "first":
                        new PlayMenu(client, new Battle(client.getLoggedAccount(), "hero",0, 500, "first")).setCurrentMenu();
                        break;
                    case "second":
                        new PlayMenu(client, new Battle(client.getLoggedAccount(),"flag6",0, 1000, "second")).setCurrentMenu();
                        break;
                    case "third":
                        new PlayMenu(client, new Battle(client.getLoggedAccount(),"flag1/2", 7, 1500, "third")).setCurrentMenu();
                        break;
                    case "exit":
                        showSinglePlayerMenu();
                        state=State.SINGLE;
                        break;
                    case "help":
                        showStoryMenu();
                        break;
                    default:
                        client.sendPrint("Invalid Command\n");
                        showStoryMenu();

                }
                break;
            case CUSTOM:
                switch (input){
                    case "first":
                        deck="first";
                        showCustomModeMenu();
                        state=State.CUSTOM_MODE;
                        break;
                    case "second":
                        deck="second";
                        showCustomModeMenu();
                        state=State.CUSTOM_MODE;
                        break;
                    case "third":
                        deck="third";
                        showCustomModeMenu();
                        state=State.CUSTOM_MODE;
                        break;
                    case "exit":
                        showSinglePlayerMenu();
                        state=State.SINGLE;
                        break;
                    case "help":
                        showCustomMenu();
                        break;
                    default:
                        client.sendPrint("Invalid Command");
                        showCustomMenu();
                }
                break;
            case CUSTOM_MODE:
                String[] inputArray=input.split(" ");
                switch (inputArray[0]){
                    case "start":
                        client.getLoggedAccount().setMainDeck(inputArray[2]);
                        if(!client.getLoggedAccount().getMainDeck().validate()){
                            client.sendPrint("selected deck is invalid");
                            break;
                        }
                        if(inputArray[inputArray.length-2].equalsIgnoreCase("flag1/2")){
                            new PlayMenu(client, new Battle(client.getLoggedAccount(),"flag1/2", Integer.parseInt(inputArray[1]), 1000, deck)).setCurrentMenu();
                        } else {
                            new PlayMenu(client, new Battle(client.getLoggedAccount(), inputArray[inputArray.length-1], 0, 1000, deck)).setCurrentMenu();
                        }
                        break;
                    case "exit":
                        showSinglePlayerMenu();
                        state=State.SINGLE;
                        break;
                    case "help":
                        showCustomModeMenu();
                        break;
                    default:
                        client.sendPrint("Invalid Command\n");
                        showCustomModeMenu();
                }
                break;
        }
        return true;
    }

    private void selectMode(String input) {
            state=State.INITIAL;
            String[] inputArray = input.split(" ");
            PlayMenu playMenu;
            Battle battle;
            switch (inputArray[0]) {
                case "hero":
                    client.sendPrint("hero mode has been selected");
                    if(opponent.getMainDeck()!=null && opponent.getMainDeck().validate()){
                        battle = new Battle("hero", 0,client.getLoggedAccount(), opponent);
                        if(client.hasPlayRequest(opponent.getClient())){
                            client.sendPrint("your opponent has requested to play with you! please go back and accept that request");
                        } else
                        new WaitForOpponentMenu(client, opponent.getClient(), battle).setCurrentMenu();
                    }
                    else client.sendPrint("selected deck for second player is invalid");
                    break;
                case "flag6":
                    client.sendPrint("flag6 mode has been selected");
                    if(opponent.getMainDeck()!=null && opponent.getMainDeck().validate()){
                        battle=new Battle("flag6",0, client.getLoggedAccount(), opponent);
                        if(client.hasPlayRequest(opponent.getClient())){
                            client.sendPrint("your opponent has requested to play with you! please go back and accept that request");
                        } else
                        new WaitForOpponentMenu(client, opponent.getClient(), battle).setCurrentMenu();
                    }
                    else client.sendPrint("selected deck for second player is invalid");
                    break;
                case "flag1/2":
                    client.sendPrint("flag1/2 mode has been selected");
                    if(opponent.getMainDeck()!=null && opponent.getMainDeck().validate()){
                        battle=new Battle("flag1/2", Integer.parseInt(inputArray[1]),client.getLoggedAccount(), opponent);
                        if(client.hasPlayRequest(opponent.getClient())){
                            client.sendPrint("your opponent has requested to play with you! please go back and accept that request");
                        } else
                        new WaitForOpponentMenu(client, opponent.getClient(), battle).setCurrentMenu();
                    }
                    else client.sendPrint("selected deck for second player is invalid");
                    break;
                case "exit":
                    state=State.INITIAL;
                    break;
                case "help":
                    showModeMenu();
                    break;
                default:
                    client.sendPrint("Invalid Command");
                    showModeMenu();
                    state=State.MODE;
            }
    }

    private void showStoryMenu() {
        client.sendPrint("*****Story Mode*****");
        client.sendPrint("1. First (Hero: White Beast, Mode: Hero Mode, Reward: 500)");
        client.sendPrint("2. Second (Hero: Zahhak, Mode: Flag6, Reward: 1000)");
        client.sendPrint("3. Third (Hero: Arash, Mode: Flag1/2, Reward: 1500)");
        client.sendPrint("4. Exit");
    }

    private void showCustomMenu(){
        client.sendPrint("*****Custom Mode*****");
        client.sendPrint("1. First (Hero: White Beast)");
        client.sendPrint("2. Second (Hero: Zahhak)");
        client.sendPrint("3. Third (Hero: Arash)");
        client.sendPrint("4. Exit");
    }

    private void showCustomModeMenu(){
        client.sendPrint("*****Custom Mode*****");
        client.getLoggedAccount().showAllDecks();
        client.sendPrint();
        client.sendPrint("Modes:");
        client.sendPrint("\t1. Hero");
        client.sendPrint("\t2. Flag6");
        client.sendPrint("\t3. Flag1/2 [number of flags]");
        client.sendPrint();
        client.sendPrint("1. Start game [deck name] [mode] [number of flags]");
        client.sendPrint("2. Exit");
    }

    private void showSinglePlayerMenu(){
        client.sendPrint("*****Single Player*****");
        client.sendPrint("1. Story");
        client.sendPrint("2. Custom game");
        client.sendPrint("3. Exit");
    }

    private void showModeMenu(){
        client.sendPrint("*****Select Battle Mode*****");
        client.sendPrint("1. Hero");
        client.sendPrint("2. Flag6");
        client.sendPrint("3. Flag1/2 [number of flags]");
        client.sendPrint("4. Exit");
    }

    void showMenu() {
        client.sendPrint("*****Battle Menu*****");
        client.sendPrint("1. Single player");
        client.sendPrint("2. Multi player");
        client.sendPrint("3. Help (show menu)");
        client.sendPrint("2. Exit");
    }

    @Override
    void setCurrentMenu() {
        super.setCurrentMenu();
    }

    private enum State{
        INITIAL,
        USER,
        MODE,
        SINGLE,
        STORY,
        CUSTOM,
        CUSTOM_MODE
    }
}

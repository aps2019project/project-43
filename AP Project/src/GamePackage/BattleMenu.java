package GamePackage;

public class BattleMenu extends GameMenu {

    private static GameMenu battleMenu = new BattleMenu();
    private State state=State.INITIAL;
    private Account opponent;
    private String deck;

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
                        Account.printUsers();
                        state=State.USER;
                        break;
                    case "help":
                        showMenu();
                        break;
                    case "exit":
                        MainMenu.goToMainMenu();
                        break;
                    default:
                        System.out.println("Invalid Command");
                }
                break;
            case USER:
                opponent = Account.getUser(input);
                if (opponent==null || opponent.getUsername().equals(Account.getLoggedAccount().getUsername())){
                    System.out.println("username is not valid!");
                    state=State.INITIAL;
                    break;
                }
                state=State.MODE;
                showModeMenu();
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
                    default:
                        System.out.println("Invalid Command");
                }
                break;
            case STORY:
                switch (input) {
                    case "first":
                        Main.setCurrentMenu(new PlayMenu(new Battle("hero",0, 500, "first")));
                        break;
                    case "second":
                        Main.setCurrentMenu(new PlayMenu(new Battle("flag6",0, 1000, "second")));
                        break;
                    case "third":
                        Main.setCurrentMenu(new PlayMenu(new Battle("flag1/2", 7, 1500, "third")));
                        break;
                    case "exit":
                        showSinglePlayerMenu();
                        state=State.SINGLE;
                        break;
                    default:
                        System.out.println("Invalid Command");
                }
                break;
            case CUSTOM:
                switch (input){
                    case "first":
                        deck="first";
                        state=State.CUSTOM_MODE;
                        break;
                    case "second":
                        deck="second";
                        state=State.CUSTOM_MODE;
                        break;
                    case "third":
                        deck="third";
                        state=State.CUSTOM_MODE;
                        break;
                    case "exit":
                        showSinglePlayerMenu();
                        state=State.SINGLE;
                        break;
                    default:
                        System.out.println("Invalid Command");
                }
                break;
            case CUSTOM_MODE:
                String[] inputArray=input.split(" ");
                switch (inputArray[0]){
                    case "start":
                        Account.getLoggedAccount().setMainDeck(inputArray[2]);
                        if(!Account.getLoggedAccount().getMainDeck().validate()){
                            System.out.println("selected deck is invalid");
                            break;
                        }
                        if(inputArray[inputArray.length-2].equalsIgnoreCase("flag1/2")){
                            Main.setCurrentMenu(new PlayMenu(new Battle("flag1/2", Integer.parseInt(inputArray[1]), 1000, deck)));
                        }else{
                            Main.setCurrentMenu(new PlayMenu(new Battle(inputArray[inputArray.length-1], 0, 1000, deck)));
                        }
                        break;
                    case "exit":
                        showSinglePlayerMenu();
                        state=State.SINGLE;
                        break;
                    default:
                        System.out.println("Invalid Command");
                }
                break;
        }
        return true;
    }

    private void selectMode(String input) {
            state=State.INITIAL;
            String[] inputArray = input.split(" ");
            switch (inputArray[0]) {
                case "hero":
                    System.out.println("hero mode has been selected");
                    if(opponent.getMainDeck()!=null && opponent.getMainDeck().validate()) Main.setCurrentMenu(new PlayMenu(new Battle("hero", 0,Account.getLoggedAccount(), opponent)));
                    else System.out.println("selected deck for second player is invalid");
                    break;
                case "flag6":
                    System.out.println("flag6 mode has been selected");
                    if(opponent.getMainDeck()!=null && opponent.getMainDeck().validate())Main.setCurrentMenu(new PlayMenu(new Battle("hero",0, Account.getLoggedAccount(), opponent)));
                    else System.out.println("selected deck for second player is invalid");
                    break;
                case "flag1/2":
                    System.out.println("flag1/2 mode has been selected");
                    if(opponent.getMainDeck()!=null && opponent.getMainDeck().validate())Main.setCurrentMenu(new PlayMenu(new Battle("hero", Integer.parseInt(inputArray[1]),Account.getLoggedAccount(), opponent)));
                    else System.out.println("selected deck for second player is invalid");
                    break;
                case "exit":
                    state=State.INITIAL;
                    break;
                default:
                    System.out.println("Invalid Command");
                    state=State.MODE;
            }
    }

    private void showStoryMenu() {
        System.out.println("*****Story Mode*****");
        System.out.println("1. First (Hero: White Beast, Mode: Hero Mode, Reward: 500)");
        System.out.println("2. Second (Hero: Zahhak, Mode: Flag6, Reward: 1000)");
        System.out.println("3. Third (Hero: Arash, Mode: Flag1/2, Reward: 1500)");
        System.out.println("4. Exit");
    }

    private void showCustomMenu(){
        System.out.println("*****Custom Mode*****");
        System.out.println("1. First (Hero: White Beast)");
        System.out.println("2. Second (Hero: Zahhak)");
        System.out.println("3. Third (Hero: Arash)");
        System.out.println("4. Exit");
    }

    private void showCustomModeMenu(){
        System.out.println("*****Custom Mode*****");
        Account.getLoggedAccount().showAllDecks();
        System.out.println();
        System.out.println("Modes:");
        System.out.println("\t1. Hero");
        System.out.println("\t2. Flag6");
        System.out.println("\t3. Flag1/2 [number of flags]");
        System.out.println();
        System.out.println("1. Start game [deck name] [mode] [number of flags]");
        System.out.println("2. Exit");
    }

    private void showSinglePlayerMenu(){
        System.out.println("*****Single Player*****");
        System.out.println("1. Story");
        System.out.println("2. Custom game");
        System.out.println("3. Exit");
    }

    private void showModeMenu(){
        System.out.println("*****Select Battle Mode*****");
        System.out.println("1. Hero");
        System.out.println("2. Flag6");
        System.out.println("3. Flag1/2 [number of flags]");
        System.out.println("4. Exit");
    }

    void showMenu() {
        System.out.println("*****Battle Menu*****");
        System.out.println("1. Single player");
        System.out.println("2. Multi player");
        System.out.println("3. Help (show menu)");
        System.out.println("2. Exit");
    }

    public static void goToBattleMenu(){
        Main.setCurrentMenu(battleMenu);
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

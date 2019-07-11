package GamePackage;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

class PlayMenu extends GameMenu {
    
    private Battle battle;
    private boolean inGraveyard = false;
    private WaitForOpponentMenu waitForOpponentMenu= null;

    PlayMenu(ClientInfo client, Battle battle){
        super(client);
        this.battle = battle;
    }

    PlayMenu(ClientInfo client, Battle battle, WaitForOpponentMenu waitForOpponentMenu){
        super(client);
        this.battle = battle;
        this.waitForOpponentMenu=waitForOpponentMenu;
    }

    @Override
    public boolean execCommand(String input) {
        String[] inputArray;
        input = input.trim().toLowerCase();
        inputArray = input.split(" ");

        if(battle.getTurnAccount()!=client.getLoggedAccount() && !input.equals("enter chatroom")){
            client.sendPrint("It is not your turn");
            return true;
        }
            if (!inGraveyard) {
                switch (inputArray[0]) {
                    case "game":
                        client.sendPrint(battle.showGameInfo());
                        break;
                    case "move":
                        battle.move(client, parseInt(inputArray[2].substring(2, inputArray[2].length() - 2)), parseInt(inputArray[3].substring(1, inputArray[3].length() - 2)));
                        break;
                    case "select":
                        battle.setSelectedCard(client, parseInt(inputArray[1]));
                        break;
                    case "show":
                        switch (inputArray[1]) {
                            case "my":
                                Force.printForces(client, battle.getMyCardsInGame());
                                break;
                            case "opponent":
                                Force.printForces(client, battle.getOpponentCardsInGame());
                                break;
                            case "card":
                                client.sendPrint(battle.showCardInfo(parseInt(inputArray[3])));
                                break;
                            case "hand":
                                client.sendPrint(battle.showHand());
                                break;
                            case "next":
                                client.sendPrint(battle.showNextCard());
                                break;
                            case "board":
                                client.sendPrint(battle.showBoard());
                                break;
                        }
                        break;
                    case "insert":
                        battle.insert(client, inputArray[1], parseInt(inputArray[3].substring(1, inputArray[3].length() - 1)), parseInt(inputArray[4].substring(0, inputArray[4].length() - 1)));
                        break;
                    case "use":
                        battle.useSpecialPower(client, parseInt(inputArray[3].substring(1, inputArray[3].length() - 1)), parseInt(inputArray[4].substring(0, inputArray[4].length() - 1)));
                        break;
                    case "enter":
                        switch (inputArray[1]) {
                            case "graveyard":
                                inGraveyard = true;
                                break;
                            case "chatroom":
                                client.sendChat();
                                new ChatMenu(client, this).setCurrentMenu();
                                break;
                        }
                        break;
                    case "end":
                        battle.endTurn(client);
                        break;
                    case "attack":
                        switch (inputArray[1]) {
                            case "combo":
                                ArrayList<Integer> ids = new ArrayList<>();
                                for (int i = 3; i < inputArray.length; i++) {
                                    ids.add(parseInt(inputArray[i]));
                                }
                                battle.attackCombo(client, parseInt(inputArray[2]), ids);
                                break;
                            default:
                                battle.attack(client, parseInt(inputArray[1]), true);
                        }
                        break;
                    case "help":
                        showMenu();
                        break;
                    case "exit":
                        client.sendPrint("Game has not ended yet!");
                        break;
                    default:
                        client.sendPrint("Invalid Command");
                        showMenu();
                }
            } else {
                switch (inputArray[0]) {
                    case "show":
                        switch (inputArray[1]) {
                            case "cards":
                                client.sendPrint(battle.showGraveyard());
                                break;
                            case "info":
                                client.sendPrint(battle.showGraveyardCard(parseInt(inputArray[2])));
                                break;
                        }
                        break;
                    case "exit":
                        inGraveyard = false;
                        break;
                    case "help":
                        showGraveyardMenu();
                        break;
                    default:
                        client.sendPrint("Invalid Command\n");
                        showGraveyardMenu();
                }
            }
        return true;
    }

    @Override
    void setCurrentMenu() {
        super.setCurrentMenu();
        if(waitForOpponentMenu!=null) waitForOpponentMenu.opponentJoined();
    }

    private Account getPlayer() {
        return client.getLoggedAccount();
    }

    public Account getOpponent() {
        return battle.getTurnAccount()==getPlayer()?battle.getNotTurnAccount():battle.getTurnAccount();
    }

    private void showGraveyardMenu() {
        client.sendPrint("1. show cards");
        client.sendPrint("2. show info [card id]");
        client.sendPrint("3. help");
        client.sendPrint("4. exit");
    }

    @Override
    void showMenu() {
        client.sendPrint("1. Game info");
        client.sendPrint("2. Show my minions");
        client.sendPrint("3. Show opponent minions");
        client.sendPrint("4. Show card info [card id]");
        client.sendPrint("5. Select [card id]");
        client.sendPrint("6. Move to ([x], [y])");
        client.sendPrint("7. Attack [opponent card id]");
        client.sendPrint("8. Attack combo [opponent card id] [my card id] [my card id] [...]");
        client.sendPrint("9. Use special power (x, y)");
        client.sendPrint("10. Show hand");
        client.sendPrint("11. Insert [card name] in (x, y)");
        client.sendPrint("12. End turn");
        client.sendPrint("13. Show info");
        client.sendPrint("14. Show next card");
        client.sendPrint("15. Enter graveyard");
        client.sendPrint("16. Enter chatroom");
        client.sendPrint("17. Show cards");
        client.sendPrint("18. End game");
        client.sendPrint("19. Show menu");
        client.sendPrint("20. Help");
        client.sendPrint("21. Exit");
    }
}

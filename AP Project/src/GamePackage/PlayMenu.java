package GamePackage;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

class PlayMenu extends GameMenu {
    
    private Battle battle;
    private boolean inGraveyard = false;
    
    PlayMenu(Battle battle){
        this.battle = battle;
    }


    @Override
    public boolean execCommand(String input) {
        String[] inputArray;
        try {
            input = input.trim().toLowerCase();
            inputArray = input.split(" ");
            if (!inGraveyard)
                switch (inputArray[0]) {
                    case "game":
                        battle.showGameInfo();
                        break;
                    case "move":
                        battle.move(parseInt(inputArray[2].substring(2, inputArray[2].length() - 2)), parseInt(inputArray[3].substring(1, inputArray[3].length() - 2)));
                        break;
                    case "select":
                        battle.setSelectedCard(parseInt(inputArray[1]));
                        break;
                    case "show":
                        switch (inputArray[1]) {
                            case "my":
                                Force.printForces(battle.getMyCardsInGame());
                                break;
                            case "opponent":
                                Force.printForces(battle.getOpponentCardsInGame());
                                break;
                            case "card":
                                battle.showCardInfo(parseInt(inputArray[3]));
                                break;
                            case "hand":
                                battle.showHand();
                                break;
                            case "next":
                                battle.showNextCard();
                                break;
                            case "board":
                                battle.showBoard();
                                break;
                        }
                        break;
                    case "insert":
                        battle.insert(inputArray[1], parseInt(inputArray[3].substring(1, inputArray[3].length() - 1)), parseInt(inputArray[4].substring(0, inputArray[4].length() - 1)));
                        break;
                    case "use":
                        battle.useSpecialPower(parseInt(inputArray[3].substring(1, inputArray[3].length() - 1)), parseInt(inputArray[4].substring(0, inputArray[4].length() - 1)));
                        break;
                    case "enter":
                        inGraveyard = true;
                        break;
                    case "end":
                        battle.endTurn();
                        break;
                    case "attack":
                        switch (inputArray[1]) {
                            case "combo":
                                ArrayList<Integer> ids = new ArrayList<>();
                                for (int i = 3; i < inputArray.length; i++) {
                                    ids.add(parseInt(inputArray[i]));
                                }
                                battle.attackCombo(parseInt(inputArray[2]), ids);
                                break;
                            default:
                                battle.attack(parseInt(inputArray[1]), true);
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
                            battle.showGraveyard();
                            break;
                        case "info":
                            battle.showGraveyardCard(parseInt(inputArray[2]));
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

            battle.showBoard();

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    void showMenu() {
        System.out.println("1. Game info");
        System.out.println("2. Show my minions");
        System.out.println("3. Show opponent minions");
        System.out.println("4. Show card info [card id]");
        System.out.println("5. Select [card id]");
        System.out.println("6. Move to ([x], [y])");
        System.out.println("7. Attack [opponent card id]");
        System.out.println("8. Attack combo [opponent card id] [my card id] [my card id] [...]");
        System.out.println("9. Use special power (x, y)");
        System.out.println("10. Show hand");
        System.out.println("11. Insert [card name] in (x, y)");
        System.out.println("12. End turn");
        System.out.println("13. Show collectables");
        System.out.println("14. Select [collectable id]");
        System.out.println("15. Show info");
        System.out.println("16. Use [location x, y]");
        System.out.println("17. Show next card");
        System.out.println("18. Enter graveyard");
        System.out.println("19. Show cards");
        System.out.println("20. End game");
        System.out.println("21. Show menu");
        System.out.println("22. Help");
        System.out.println("23. Exit");
    }
}

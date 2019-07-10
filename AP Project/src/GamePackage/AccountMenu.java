package GamePackage;

public class AccountMenu extends GameMenu {

    private State state = State.INITIAL;
    private String username;

    AccountMenu(ClientInfo client) {
        super(client);
    }

    @Override
    public boolean execCommand(String input) {
        input = input.trim().toLowerCase();
        if (client.getLoggedAccount() == null) {
            switch (state){
                case INITIAL:
                    switch (input) {
                        case "create account":
                            client.sendPrint("Please Enter UserName");
                            state=State.USERNAME_C;
                            break;
                        case "login":
                            client.sendPrint("please enter Your Username");
                            state=State.USERNAME_L;
                            break;
                        case "help":
                            showMenu();
                            break;
                        case "exit":
                            return false;
                        default:
                            client.sendPrint("Invalid Command\n");
                            showMenu();
                    }
                    break;
                case USERNAME_C:
                    username=input;
                    client.sendPrint("And Your Password");
                    state=State.PASSWORD_C;
                    break;
                case PASSWORD_C:
                    client.server.createAccount(client, username, input);
                    state=State.INITIAL;
                    break;
                case USERNAME_L:
                    username=input;
                    client.sendPrint("Please enter Your password");
                    state=State.PASSWORD_L;
                    break;
                case PASSWORD_L:
                    client.server.login(client, username, input);
                    state=State.INITIAL;
                    break;
            }
        } else {
            switch (input) {
                case "main menu":
                    client.mainMenu.setCurrentMenu();
                    break;
                case "show leaderboard":
                    client.sendPrint(client.server.showLeaderboard());
                    break;
                case "save":
                    //todo
                    break;
                case "logout":
                    client.server.logout(client);
                    break;
                case "help":
                    showMenu();
                    break;
                case "exit":
                    return false;
                default:
                    client.sendPrint("Invalid Command\n");
                    showMenu();
            }
        }
        return true;
    }

    void showMenu() {
        if (client.getLoggedAccount() == null) {
            client.sendPrint("1. Create account");
            client.sendPrint("2. Login");
            client.sendPrint("3. Help (show menu)");
            client.sendPrint("4. Exit");
        } else {
            client.sendPrint("1. Main menu");
            client.sendPrint("2. Show leaderboard");
            client.sendPrint("3. Save");
            client.sendPrint("4. Logout");
            client.sendPrint("5. Help (show menu)");
            client.sendPrint("6. Exit");
        }
    }


    private enum State{
        INITIAL,
        USERNAME_C,
        PASSWORD_C,
        USERNAME_L,
        PASSWORD_L
    }
}

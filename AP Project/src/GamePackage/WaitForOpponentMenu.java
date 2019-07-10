package GamePackage;

public class WaitForOpponentMenu extends GameMenu{
    private Battle battle;
    ClientInfo client;
    private ClientInfo opponentClient;
    private PlayMenu opponentPlayMenu;

    WaitForOpponentMenu(ClientInfo client, ClientInfo opponentClient, Battle battle) {
        super(client);
        this.battle=battle;
        this.client=client;
        this.opponentClient=opponentClient;
        opponentPlayMenu=new PlayMenu(opponentClient, battle, this);
        opponentClient.addToPlayRequests(opponentPlayMenu);
        client.sendWaitOrCancel();
    }

    public void opponentJoined(){
        client.sendFriendJoined();
        new PlayMenu(client, battle).setCurrentMenu();
    }

    private void cancel(){
        client.sendCancelWaiting();
        opponentClient.removePlayRequest(opponentPlayMenu);
        client.battleMenu.setCurrentMenu();
    }

    @Override
    boolean execCommand(String input) {
        input=input.trim().toLowerCase();
        switch (input){
            case "cancel":
                cancel();
                break;
            case "help":
                showMenu();
                break;
            case "exit":
                cancel();
                break;
            default:
                client.sendPrint("Invalid Command\n");
                showMenu();
        }
        return true;
    }

    @Override
    void showMenu() {
        client.sendPrint("Please wait or type \"Cancel\" ..");
    }
}

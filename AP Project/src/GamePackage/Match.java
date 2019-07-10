package GamePackage;

public class Match {
    private Account player;
    private Account opponent;
    private boolean winner;
    private int time = 0; //turns count

    public int getTime() {
        return time;
    }

    public Account getWinner(){
        return winner?player:opponent;
    }

    public Match(Account player, Account opponent, boolean winner, int time) {
        this.player = player;
        this.opponent = opponent;
        this.winner=winner;
        this.time=time;
    }

}
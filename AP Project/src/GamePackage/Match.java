package GamePackage;

public class Match {
    private Account[] players;
    private boolean winner; //true for Player 1, false for Player 2
    private int time = 0; //turns count

    public int getTime() {
        return time;
    }

    public Match(Account[] players, boolean winner, int time) {
        this.players = players;
        this.winner=winner;
        this.time=time;
    }

    public Account getWinner() {
        return winner ? players[0] : players[1];
    }

}

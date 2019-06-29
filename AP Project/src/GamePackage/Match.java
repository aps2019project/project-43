package GamePackage;

public class Match {
    private Account opponent;
    private boolean winner;
    private int time = 0; //turns count

    public int getTime() {
        return time;
    }

    public Account getWinner(){
        return winner?Account.getLoggedAccount():opponent;
    }

    public Match(Account opponent, boolean winner, int time) {
        this.opponent = opponent;
        this.winner=winner;
        this.time=time;
    }

}
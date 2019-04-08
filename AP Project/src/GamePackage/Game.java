package GamePackage;

public class Game {
    private static Cell[][] cells = new Cell[5][9];
    private static Account[] players = new Account[2];
    // private boolean turn = false; // false for player 1 and true for player 2
    private int turn = 1; // Odd for player 1's turn and even for player 2's turn

    private int gameState; // 0 for when the game is not over yet, 1 for when player 1 wins, and 2 for when player 2 wins

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public static void setCells(Cell[][] cells) {
        Game.cells = cells;
    }

    public static Account[] getPlayers() {
        return players;
    }

    public static void setPlayers(Account[] players) {
        Game.players = players;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void checkIfGameOver() {

    }
}

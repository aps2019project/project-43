package GamePackage;

import java.util.ArrayList;

public class Game {
    private Cell[][] cells = new Cell[5][9];
    private Account[] players = new Account[2];
    private ArrayList<Card> player1DeadCards = new ArrayList<>();
    private ArrayList<Card> player2DeadCards = new ArrayList<>();
    private int turn = 1; // Odd for player 1's turn and even for player 2's turn
    private int gameState; // 0 for when the game is not over yet, 1 for when player 1 wins, and 2 for when player 2 wins

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Account[] getPlayers() {
        return players;
    }

    public void setPlayers(Account[] players) {
        this.players = players;
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

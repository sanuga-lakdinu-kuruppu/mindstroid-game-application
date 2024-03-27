package com.mindstroid.mindstroidgameapplication.games.tictactoe.service;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@Service
public class LiveTicTacToeGame {

    private final int BOARD_SIZE = 3;
    private char[][] board;
    private List<Gamer> gamersList;
    private Gamer gameOwner;
    private String gameId;
    private char human;
    private char computer;

    private char winner;

    private static final char PLAYER_X_SYMBOL = 'X';
    private static final char PLAYER_O_SYMBOL = 'O';

    public static char getPlayerXSymbol() {
        return PLAYER_X_SYMBOL;
    }

    public static char getPlayerOSymbol() {
        return PLAYER_O_SYMBOL;
    }

    public LiveTicTacToeGame(String gameOwnerUsername, String gameId) {
        this.gameId = gameId;
        this.board = new char[3][3];
        this.gamersList = new ArrayList<>();
        this.gameOwner = new Gamer(0, gameOwnerUsername, "");
        gamersList.add(this.gameOwner);
        initializeBoard();
    }
    public LiveTicTacToeGame() {
    }

    public void resetGame(String username, String gameId, char humanSymbol, char computerSymbol) {
        if (gamersList == null) {
            gamersList = new ArrayList<>();
        } else {
            gamersList.clear();
        }
        this.gameOwner = new Gamer();
        setNameForGamer(gameOwner, username);
        this.gameId = gameId;
        setPlayerSymbols(humanSymbol, computerSymbol);
        gamersList.add(this.gameOwner);
        initializeBoard();
    }

    private void setPlayerSymbols(char humanSymbol, char computerSymbol) {
        this.human = humanSymbol;
        this.computer = computerSymbol;
    }


    private void setNameForGamer(Gamer gamer, String username) {
        try {
            Field nameField = Gamer.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(gamer, username);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Error setting name for Gamer: " + e.getMessage());
        }
    }

    public void initializeBoard() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = '-';
            }
        }
    }

    public String printBoard(){
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        return board[0][0] + " " + board[0][1] + " " + board[0][2] + "\n" +
                board[1][0] + " " + board[1][1] + " " + board[1][2] + "\n" +
                board[2][0] + " " + board[2][1] + " " + board[2][2];
    }

    public boolean makeHumanMove(int row, int col, char player) {
        if (isValidMove(row, col) && board[row][col] == '-') {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    public int[] makeComputerMove() {
        List<int[]> availableMoves = new ArrayList<>();

        // Check for winning move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = computer;
                    if (isWin(computer)) {
                        board[i][j] = '-';
                        return new int[]{i, j};
                    }
                    board[i][j] = '-';
                }
            }
        }

        // Check for blocking move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = human;
                    if (isWin(human)) {
                        board[i][j] = '-';
                        return new int[]{i, j};
                    }
                    board[i][j] = '-';
                }
            }
        }

        // Find available moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }

        // Select a random move from available moves
        if (!availableMoves.isEmpty()) {
            int[] randomMove = availableMoves.get((int) (Math.random() * availableMoves.size()));
            return randomMove;
        } else {
            return new int[]{0, 0};
        }
    }


    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    public boolean isGameOver() {
        return isWin(human) || isWin(computer) || isDraw();
    }

    public boolean isWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;  //row
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;  //col
            }
            if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
                return true;  //diagonal
            }
            if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
                return true;  //diagonal
            }
        }
        return false;
    }

    public boolean isDraw() {
        if (isWin(human) || isWin(computer)) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
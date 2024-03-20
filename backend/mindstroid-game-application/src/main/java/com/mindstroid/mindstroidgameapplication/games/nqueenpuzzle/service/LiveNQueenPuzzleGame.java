package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CorrectSolution;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.Gamers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@AllArgsConstructor
@Data
@Builder
public class LiveNQueenPuzzleGame {

    private static Logger audit = LogManager.getLogger("audit-log");

    private final Gamers gameOwner;
    private final String gameId;
    private int correctAnswerCount;
    private final int chessBoardSize = 8;
    private final int[][] initialChessBoard;
    private final List<Gamers> gamersList;
    private final List<CorrectSolution> solutionList;

    public LiveNQueenPuzzleGame(String gameOwner, String gamerId, String gameId) {
        this.gameId = gameId;
        initialChessBoard = new int[chessBoardSize][chessBoardSize];
        solutionList = new ArrayList<CorrectSolution>();
        gamersList = new ArrayList<Gamers>();

        this.gameOwner = Gamers.builder().username(gameOwner).gamerId(gamerId).build();
        gamersList.add(this.gameOwner);
    }

    public void createLiveGame() {
        try {
            findQueenPattern(initialChessBoard, 0);
        } catch (Exception e) {
            audit.info("game,n-queen-game,meth-createLiveGame,error,exception," + e.getMessage());
        }
    }

    private void findQueenPattern(int[][] tmpChessBoard, int currentColumn) {
        try {
            if (currentColumn == chessBoardSize) {
                //because tmpChessBoard is updated after adding to the list, it will change the added arrays also. so we have create a copy of that.
                int[][] chessBoardCopy = new int[chessBoardSize][chessBoardSize];
                for (int i = 0; i < chessBoardSize; i++) {
                    chessBoardCopy[i] = Arrays.copyOf(tmpChessBoard[i], chessBoardSize);
                }
                CorrectSolution correctSolution = new CorrectSolution();
                correctSolution.setSolution(chessBoardCopy);
                correctSolution.setIndex(solutionList.size());
                correctSolution.setIdentify(false);
                solutionList.add(correctSolution);
            }

            for (int row = 0; row < chessBoardSize; row++) {
                if (checkValidity(tmpChessBoard, row, currentColumn)) {
                    tmpChessBoard[row][currentColumn] = 1;
                    findQueenPattern(tmpChessBoard, currentColumn + 1);
                    tmpChessBoard[row][currentColumn] = 0;
                }
            }
        } catch (Exception e) {
            audit.info("game,n-queen-game,meth-findQueenPattern,currentColumn," + currentColumn + ",error,exception," + e.getMessage());
        }
    }

    private boolean checkValidity(int[][] tmpChessBoard, int row, int column) {
        try {
            for (int i = 0; i < column; i++) {
                if (tmpChessBoard[row][i] == 1)
                    return false;
            }

            for (int i = row, j = column; i >= 0 && j >= 0; i--, j--) {
                if (tmpChessBoard[i][j] == 1)
                    return false;
            }

            for (int i = row, j = column; i < chessBoardSize && j >= 0; i++, j--) {
                if (tmpChessBoard[i][j] == 1)
                    return false;
            }
            return true;
        } catch (Exception e) {
            audit.info("game,n-queen-game,meth-checkValidity,row," + row + ",col," + column + ",error,exception," + e.getMessage());
            return false;
        }
    }
}

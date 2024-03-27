package com.mindstroid.mindstroidgameapplication.games.tictactoe.service;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import com.mindstroid.mindstroidgameapplication.common.enums.GameTypes;
import com.mindstroid.mindstroidgameapplication.games.tictactoe.entity.TicTacToeGame;
import com.mindstroid.mindstroidgameapplication.games.tictactoe.repository.TicTacToeGameRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TicTacToeGameService {
    @Autowired
    private LiveTicTacToeGame liveTicTacToeGame;
    @Autowired
    private TicTacToeGameRepository ticTacToeGameRepository;
    private String lastMove;
    private char currentPlayerSymbol;

    @PersistenceContext
    private EntityManager entityManager;

    public TicTacToeGameService(LiveTicTacToeGame liveTicTacToeGame, TicTacToeGameRepository ticTacToeGameRepository) {
        this.liveTicTacToeGame = liveTicTacToeGame;
        this.ticTacToeGameRepository = ticTacToeGameRepository;
    }
    @Transactional
    public boolean makeMove(int row, int col, String playerName, char playerSymbol) {
        try {
            boolean moveMade = false;

            currentPlayerSymbol = playerSymbol;
            char computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';

            if (liveTicTacToeGame != null && ticTacToeGameRepository != null) {
                if (playerName.equals("computer")) {
                    int[] computerMove = liveTicTacToeGame.makeComputerMove();
                    if (computerMove != null && computerMove.length == 2) {
                        moveMade = liveTicTacToeGame.makeHumanMove(computerMove[0], computerMove[1], computerSymbol);
                        if (moveMade) {
                            this.lastMove = "Computer made a move at row " + computerMove[0] + " and column " + computerMove[1];
                        }
                    }
                } else {
                    moveMade = liveTicTacToeGame.makeHumanMove(row, col, playerSymbol);
                    if (moveMade) {
                        this.lastMove = playerName + " made a move at row " + row + " and column " + col;
                    }
                }

                if (liveTicTacToeGame.isGameOver()) {
                    String gameStatus;
                    if (!liveTicTacToeGame.isDraw() && playerName.equals("computer")) {
                        gameStatus = "Lost";
                    } else if (!liveTicTacToeGame.isDraw()) {
                        gameStatus = "Win";
                    } else {
                        gameStatus = "Draw";
                    }
                    saveGameResult(playerName, gameStatus);
                }
            } else {
                throw new RuntimeException("Dependencies not initialized");
            }

            return moveMade;
        } catch (Exception e) {
            System.err.println("Error making move: " + e.getMessage());
            return false;
        }
    }
    @Transactional
    private void saveGameResult(String playerName, String gameStatus) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        ticTacToeGame.setWinStatus("Win".equals(gameStatus));
        ticTacToeGame.setGameResult(gameStatus);
        ticTacToeGame.setGameTypes(GameTypes.TIC_TAC_TOE_GAME);
        ticTacToeGame.setStartedTime(LocalDateTime.now());
        ticTacToeGame.setLastUpdatedTime(LocalDateTime.now());
        ticTacToeGame.setStatus(true);
        Gamer gameOwner = new Gamer();
        gameOwner.setName(playerName);
        entityManager.persist(gameOwner);

        ticTacToeGame.setGameOwner(gameOwner);
        ticTacToeGameRepository.save(ticTacToeGame);
    }
    public boolean isGameOver() {
        return liveTicTacToeGame.isGameOver();
    }
    public String getCurrentGameState() {
        return liveTicTacToeGame.printBoard();
    }
    public String getGameResult() {
        if (liveTicTacToeGame.isGameOver()) {
            if (liveTicTacToeGame.isWin(currentPlayerSymbol)) {
                return "Win";
            } else if (liveTicTacToeGame.isDraw()) {
                return "Draw";
            } else {
                return "Lost";
            }
        } else {
            return "Game still in progress";
        }
    }
    public String getLastMove() {
        return lastMove;
    }
}
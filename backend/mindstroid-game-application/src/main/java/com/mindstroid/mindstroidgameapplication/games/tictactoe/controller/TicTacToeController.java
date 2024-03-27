package com.mindstroid.mindstroidgameapplication.games.tictactoe.controller;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import com.mindstroid.mindstroidgameapplication.common.repository.GamerRepository;
import com.mindstroid.mindstroidgameapplication.games.tictactoe.service.LiveTicTacToeGame;
import com.mindstroid.mindstroidgameapplication.games.tictactoe.service.TicTacToeGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {

    private final TicTacToeGameService ticTacToeGameService;
    private final LiveTicTacToeGame ticTacToeGame;

    private final GamerRepository gamerRepository;

    public TicTacToeController(LiveTicTacToeGame ticTacToeGame, TicTacToeGameService ticTacToeGameService, GamerRepository gamerRepository) {
        this.ticTacToeGame = ticTacToeGame;
        this.ticTacToeGameService = ticTacToeGameService;
        this.gamerRepository = gamerRepository;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startGame(@RequestParam String gameOwnerUsername,
                                            @RequestParam String gameId,
                                            @RequestParam char humanSymbol) {
        if (gameOwnerUsername.isEmpty() || gameId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Both gameOwnerUsername and gameId parameters are required.");
        }
        Gamer gamer=new Gamer();
        gamer.setName(gameOwnerUsername);
        gamerRepository.save(gamer);

        char computerSymbol = (humanSymbol == 'X') ? 'O' : 'X';

        ticTacToeGame.resetGame(gameOwnerUsername, gameId, humanSymbol, computerSymbol);
        if (humanSymbol == 'O') {
            int[] computerMove = ticTacToeGame.makeComputerMove();
            if (computerMove != null && computerMove.length == 2) {
                ticTacToeGameService.makeMove(computerMove[0], computerMove[1], "computer", humanSymbol);
            }
        }
        return ResponseEntity.ok("Game started by " + gameOwnerUsername);
    }

    @PostMapping("/move")
    public ResponseEntity<String> makeMove(@RequestParam String playerName,
                                           @RequestParam(required = false) Integer row,
                                           @RequestParam(required = false) Integer col) {
        if (playerName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name parameter is required.");
        }

        char playerSymbol = ticTacToeGame.getHuman();

        if (row != null && col != null) {
            boolean moveMade = ticTacToeGameService.makeMove(row, col, playerName, playerSymbol);
            if (moveMade) {
                String moveMessage = playerName + " has made a move at row " + row + " and column " + col;
                if (ticTacToeGameService.isGameOver()) {
                    return getGameStatus();
                } else {
                    int[] computerMove = ticTacToeGame.makeComputerMove();
                    if (computerMove != null && computerMove.length == 2) {
                        int computerRow = computerMove[0];
                        int computerCol = computerMove[1];
                        boolean computerMoveMade = ticTacToeGameService.makeMove(computerRow, computerCol, "computer", playerSymbol);
                        if (computerMoveMade) {
                            String computerMoveMessage = "Computer has made a move at row " + computerRow + " and column " + computerCol;
                            return ResponseEntity.ok(moveMessage + "\n" + computerMoveMessage);
                        } else {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error making computer move");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error making computer move");
                    }
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid move");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Row and column parameters are required.");
        }
    }
    @GetMapping("/status")
    public ResponseEntity<String> getGameStatus() {
        if (ticTacToeGameService.isGameOver()) {
            String gameResult = ticTacToeGameService.getGameResult();
            if (gameResult.equals("Win")) {
                return ResponseEntity.ok("You win!");
            } else if (gameResult.equals("Lost")) {
                return ResponseEntity.ok("You lost!");
            } else {
                return ResponseEntity.ok("It's a draw!");
            }
        } else {
            String gameState = ticTacToeGameService.getCurrentGameState();
            String lastMove = ticTacToeGameService.getLastMove();
            return ResponseEntity.ok(gameState + "\nLast Move: " + lastMove);
        }
    }
}

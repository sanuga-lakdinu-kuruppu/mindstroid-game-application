package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.service;

import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CorrectSolution;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CreatedNQueenPuzzleGameResponse;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.Gamers;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.MatchAnswerRequest;
import com.mindstroid.mindstroidgameapplication.utilities.GameIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NQueenPuzzleGameService {

    private static Logger audit = LogManager.getLogger("audit-log");
    private static final List<LiveNQueenPuzzleGame> liveNQueenPuzzleGames = new ArrayList<>();

    public ResponseEntity<CreatedNQueenPuzzleGameResponse> createLiveGame(String gameOwner) {

        String gameId = GameIdGenerator.generateGameId(5);

        LiveNQueenPuzzleGame newGame = new LiveNQueenPuzzleGame(gameOwner, gameId);
        newGame.createLiveGame();

        liveNQueenPuzzleGames.add(newGame);

        var createdGame = CreatedNQueenPuzzleGameResponse
                .builder()
                .gameId(newGame.getGameId())
                .gameOwner(newGame.getGameOwner().getUsername())
                .initialChessBoard(newGame.getInitialChessBoard())
                .numberOfSolutions(newGame.getSolutionList().size())
                .gamersList(newGame.getGamersList())
                .build();

        return new ResponseEntity<>(createdGame, HttpStatus.OK);
    }

    public ResponseEntity<String> closeTheGame(String user, String gameId) {
        boolean isRemoved = false;
        LiveNQueenPuzzleGame removableGame = null;

        for (LiveNQueenPuzzleGame game : liveNQueenPuzzleGames) {
            if (game.getGameId().equals(gameId) && game.getGameOwner().getUsername().equals(user)) {
                removableGame = game;
                break;
            }
        }

        if (removableGame != null) {
            isRemoved = liveNQueenPuzzleGames.remove(removableGame);
        }

        return (isRemoved) ? new ResponseEntity<>("game is closed", HttpStatus.OK)
                : new ResponseEntity<>("error occured!", HttpStatus.CONFLICT);
    }

    public List<String> getLiveGames() {
        List<String> live = new ArrayList<>();
        for (LiveNQueenPuzzleGame game : liveNQueenPuzzleGames) {
            live.add(game.getGameId());
        }
        return live;
    }

    public ResponseEntity<CreatedNQueenPuzzleGameResponse> joinGame(String user, String gameId) {
        for (LiveNQueenPuzzleGame game : liveNQueenPuzzleGames) {
            if (game.getGameId().equals(gameId)) {
                for (Gamers gamer : game.getGamersList()) {
                    if (!gamer.getUsername().equals(user)) {
                        Gamers newGamer = Gamers
                                .builder()
                                .username(user)
                                .build();
                        game.getGamersList().add(newGamer);

                        var createdGame = CreatedNQueenPuzzleGameResponse
                                .builder()
                                .gameId(game.getGameId())
                                .gameOwner(game.getGameOwner().getUsername())
                                .initialChessBoard(game.getInitialChessBoard())
                                .numberOfSolutions(game.getSolutionList().size())
                                .gamersList(game.getGamersList())
                                .build();

                        return new ResponseEntity<>(createdGame, HttpStatus.OK);
                    } else {
                        // this user is already a gamer
                    }
                }
            } else {
                //there is no game like this
            }
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    public ResponseEntity<CorrectSolution> matchAnswer(MatchAnswerRequest matchAnswerRequest) {
        LiveNQueenPuzzleGame playingGame = null;
        boolean isGamerAvailable = false;
        for (LiveNQueenPuzzleGame game : liveNQueenPuzzleGames) {
            if (matchAnswerRequest.getGameId().equals(game.getGameId())) {
                playingGame = game;
            } else {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }

        if (playingGame != null) {
            for (Gamers gamer : playingGame.getGamersList()) {
                if (gamer.getUsername().equals(matchAnswerRequest.getUser())) {
                    isGamerAvailable = true;
                } else {
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }

            if (isGamerAvailable) {
                for (CorrectSolution solution : playingGame.getSolutionList()) {
                    if (Arrays.deepEquals(matchAnswerRequest.getAnswer(), solution.getSolution()) && !solution.isIdentify()) {

                        solution.setIdentify(true);
                        return new ResponseEntity<>(solution, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                    }
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
}

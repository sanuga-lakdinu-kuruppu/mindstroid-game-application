package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import com.mindstroid.mindstroidgameapplication.common.entity.LiveGame;
import com.mindstroid.mindstroidgameapplication.common.enums.GameTypes;
import com.mindstroid.mindstroidgameapplication.common.repository.GameRepository;
import com.mindstroid.mindstroidgameapplication.common.repository.GamerRepository;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.*;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.entity.NQueenPuzzleAnswer;
import com.mindstroid.mindstroidgameapplication.utilities.GameIdGenerator;
import com.mindstroid.mindstroidgameapplication.utilities.ScoreCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NQueenPuzzleGameService {

    private static Logger audit = LogManager.getLogger("audit-log");
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamerRepository gamerRepository;
    private static final List<LiveNQueenPuzzleGame> liveNQueenPuzzleGames = new ArrayList<>();

    public ResponseEntity<CreatedNQueenPuzzleGameResponse> createLiveGame(String gameOwner) {

        try {
            if (gameOwner == null) {
                audit.info("game,n-queen-game,controller-/create,request,from," + gameOwner + ",error,game owner  cannot be null");
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            for (LiveNQueenPuzzleGame liveNQueenPuzzleGame : liveNQueenPuzzleGames) {
                if (gameOwner.equals(liveNQueenPuzzleGame.getGameOwner().getUsername())) {
                    audit.info("game,n-queen-game,controller-/create,request,from," + gameOwner + ",error,same game owner  " +
                            "cannot create concurrent games");
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }

            String gameId = GameIdGenerator.generateGameId(5);
            String gamerId = GameIdGenerator.generateGameId(10);

            LiveNQueenPuzzleGame newGame = new LiveNQueenPuzzleGame(gameOwner, gamerId, gameId);
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

            var gamer = Gamer.builder().name(gameOwner).gamerId(gamerId).build();
            LocalDateTime now = LocalDateTime.now();

            var liveGame = LiveGame
                    .builder()
                    .gameTypes(GameTypes.NQUEEN_PUZZLE_GAME)
                    .status(true)
                    .gameOwner(gamer)
                    .gameId(gameId)
                    .startedTime(now)
                    .lastUpdatedTime(now)
                    .build();

            gamerRepository.save(gamer);
            gameRepository.save(liveGame);

            audit.info("game,n-queen-game,controller-/create,request,from," + gameOwner + ",game created,id," + createdGame.getGameId() + ",ok");
            return new ResponseEntity<>(createdGame, HttpStatus.OK);
        } catch (Exception e) {
            audit.info("game,n-queen-game,controller-/create,request,from," + gameOwner + ",error," + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<String> closeTheGame(String user, String gameId) {
        boolean isRemoved = false;
        LiveNQueenPuzzleGame removableGame = null;
        try {
            if (user == null || gameId == null) {
                audit.info("game,n-queen-game,controller-/close,request,from," + user + ",error,user or game id cannot be null");
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            for (LiveNQueenPuzzleGame game : liveNQueenPuzzleGames) {
                if (game.getGameId().equals(gameId) && game.getGameOwner().getUsername().equals(user)) {
                    removableGame = game;
                    break;
                }
            }

            if (removableGame != null) {
                isRemoved = liveNQueenPuzzleGames.remove(removableGame);
            }

            if (isRemoved) {

                var obj = gameRepository.findByGameId(gameId);
                if (obj.isPresent()) {
                    LiveGame liveGame = obj.get();
                    liveGame.setStatus(false);
                    gameRepository.save(liveGame);
                }

                audit.info("game,n-queen-game,controller-/close,request,from," + user + ",success,ok");
                return new ResponseEntity<>("game is closed", HttpStatus.OK);
            }

            audit.info("game,n-queen-game,controller-/close,request,from," + user + ",error,cannot find the game");
            return new ResponseEntity<>("error occured!", HttpStatus.CONFLICT);
        } catch (Exception e) {
            audit.info("game,n-queen-game,controller-/close,request,from," + user + ",error," + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
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

    public ResponseEntity<CorrectAnswerResponse> matchAnswer(MatchAnswerRequest matchAnswerRequest) {
        LiveNQueenPuzzleGame playingGame = null;
        Gamers availableGamer = null;
        LocalDateTime now = LocalDateTime.now();

        try {

            if (matchAnswerRequest == null) {
                audit.info("game,n-queen-game,controller-/match,request,from,error,bad request");
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            for (LiveNQueenPuzzleGame game : liveNQueenPuzzleGames) {
                if (matchAnswerRequest.getGameId().equals(game.getGameId())) {
                    playingGame = game;
                }
            }

            if (playingGame != null) {
                for (Gamers gamer : playingGame.getGamersList()) {
                    if (gamer.getUsername().equals(matchAnswerRequest.getUser())) {
                        availableGamer = gamer;
                    }
                }

                if (availableGamer != null) {
                    for (CorrectSolution solution : playingGame.getSolutionList()) {
                        if (Arrays.deepEquals(matchAnswerRequest.getAnswer(), solution.getSolution())) {
                            if (solution.isIdentify()) {
                                audit.info("game,n-queen-game,controller-/match,request,from," + matchAnswerRequest.getUser()
                                        + ",already answered");
                                return new ResponseEntity<>(null, HttpStatus.FOUND);
                            }

                            int count = playingGame.getCorrectAnswerCount();
                            playingGame.setCorrectAnswerCount(count + 1);

                            solution.setIdentify(true);
                            if (availableGamer.getCorrectSolutionList() == null) {
                                List<CorrectSolution> list = new ArrayList<>();
                                list.add(solution);
                            } else {
                                availableGamer.getCorrectSolutionList().add(solution);
                            }

                            //change the flag after correctly identify all the answers
                            if (playingGame.getCorrectAnswerCount() >= playingGame.getSolutionList().size()) {
                                playingGame.setCorrectAnswerCount(0);
                                for (CorrectSolution correctSolution : playingGame.getSolutionList()) {
                                    correctSolution.setIdentify(false);
                                }
                            }

                            int value = availableGamer.getScore() + ScoreCalculator.calculateScore(matchAnswerRequest.getTimeTakenInSeconds());
                            availableGamer.setScore(value);

                            var response = CorrectAnswerResponse
                                    .builder()
                                    .index(solution.getIndex())
                                    .solution(solution.getSolution())
                                    .isIdentify(true)
                                    .updatedScore(availableGamer.getScore())
                                    .build();


                            //convert 2d array into a string
                            ObjectMapper mapper = new ObjectMapper();
                            String answerString = mapper.writeValueAsString(matchAnswerRequest.getAnswer());


                            var correctAnswer = NQueenPuzzleAnswer
                                    .builder()
                                    .answeredTime(now)
//                                    .liveGame()
                                    .build();

                            audit.info("game,n-queen-game,controller-/match,request,from," + matchAnswerRequest.getUser() + ",ok");
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }

                    }
                    //return why this is not matched
                    audit.info("game,n-queen-game,controller-/match,request,from," + matchAnswerRequest.getUser() + ",not a correct answer");
//                    createErrorResponse();
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            }

            audit.info("game,n-queen-game,controller-/match,request,from," + matchAnswerRequest.getUser() + ",error,cannot find the game");
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (Exception e) {
            audit.info("game,n-queen-game,controller-/match,request,from,error," + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}

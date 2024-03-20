package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.controller;

import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CorrectAnswerResponse;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CorrectSolution;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CreatedNQueenPuzzleGameResponse;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.service.NQueenPuzzleGameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.MatchAnswerRequest;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/n-queen-puzzle")
public class NQueenPuzzleGameController {

    private static Logger audit = LogManager.getLogger("audit-log");

    @Autowired
    private NQueenPuzzleGameService nQueenPuzzleGameService;

    @PostMapping("/create")
    public ResponseEntity<CreatedNQueenPuzzleGameResponse> createLiveGame(@RequestParam String gameOwner) {

        try {
            audit.info("game,n-queen-game,controller-/create,request,from," + gameOwner + ",ok");
            return nQueenPuzzleGameService.createLiveGame(gameOwner);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/close")
    public ResponseEntity<String> closeGame(@RequestParam String user, @RequestParam String gameId) {
        try {
            audit.info("game,n-queen-game,controller-/close,request,user," + user + ",ok");
            return nQueenPuzzleGameService.closeTheGame(user, gameId);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/live-games")
    public List<String> getLiveGames() {
        return nQueenPuzzleGameService.getLiveGames();
    }

    @PostMapping("/join-game")
    public ResponseEntity<CreatedNQueenPuzzleGameResponse> joinTheGame(@RequestParam String user, @RequestParam String gameId) {
        return nQueenPuzzleGameService.joinGame(user, gameId);
    }

    @PostMapping("/match")
    public ResponseEntity<CorrectAnswerResponse> matchAnswers(@RequestBody MatchAnswerRequest matchAnswerRequest) {
        return nQueenPuzzleGameService.matchAnswer(matchAnswerRequest);
    }

//    @DeleteMapping("/left")
//    public void leftUser(@RequestParam String user, @RequestParam String gameId) {
//
//    }
}

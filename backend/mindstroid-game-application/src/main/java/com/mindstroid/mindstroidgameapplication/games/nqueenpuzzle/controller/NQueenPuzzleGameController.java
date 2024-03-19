package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.controller;

import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CorrectSolution;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CreatedNQueenPuzzleGameResponse;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.service.NQueenPuzzleGameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        return nQueenPuzzleGameService.createLiveGame(gameOwner);
    }

    @DeleteMapping("/close")
    public ResponseEntity<String> closeGame(@RequestParam String user, @RequestParam String gameId) {
        return nQueenPuzzleGameService.closeTheGame(user, gameId);
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
    public ResponseEntity<CorrectSolution> matchAnswers(@RequestBody MatchAnswerRequest matchAnswerRequest) {
        return nQueenPuzzleGameService.matchAnswer(matchAnswerRequest);
    }

//    @DeleteMapping("/left")
//    public void leftUser(@RequestParam String user, @RequestParam String gameId) {
//
//    }
}

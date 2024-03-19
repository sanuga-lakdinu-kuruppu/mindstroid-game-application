package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.controller;

import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.NQueenPuzzleGameCreateRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/n-queen-puzzle")
public class NQueenPuzzleGameController {

    @PostMapping("/create")
    public void createLiveGame(@RequestBody NQueenPuzzleGameCreateRequest request) {

    }
}

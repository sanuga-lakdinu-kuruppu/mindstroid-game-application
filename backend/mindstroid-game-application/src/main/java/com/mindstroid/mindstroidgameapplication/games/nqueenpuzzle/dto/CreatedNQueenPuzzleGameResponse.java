package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatedNQueenPuzzleGameResponse {
    private String gameId;
    private String gameOwner;
    private int numberOfSolutions;
    private List<Gamers> gamersList;
    private int[][] initialChessBoard;
}

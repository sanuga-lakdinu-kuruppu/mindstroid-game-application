package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Gamers {

    private String gamerId;
    private String username;
    private List<CorrectSolution> correctSolutionList;
    private int score;
}

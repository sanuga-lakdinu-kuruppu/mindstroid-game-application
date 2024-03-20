package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
public class CorrectSolution {
    private int index;
    private int[][] solution;
    private boolean isIdentify;
}

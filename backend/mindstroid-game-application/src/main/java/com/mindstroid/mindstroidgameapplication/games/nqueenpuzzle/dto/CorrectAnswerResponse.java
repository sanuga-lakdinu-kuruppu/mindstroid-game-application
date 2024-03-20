package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CorrectAnswerResponse {
    private int index;
    private int[][] solution;
    private boolean isIdentify;
    private int updatedScore;
}

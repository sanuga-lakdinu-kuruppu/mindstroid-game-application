package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MatchAnswerRequest {
    private int[][] answer;
    private String user;
    private String gameId;
}

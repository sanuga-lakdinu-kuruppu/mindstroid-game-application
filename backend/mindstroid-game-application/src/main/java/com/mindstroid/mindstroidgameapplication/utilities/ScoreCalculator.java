package com.mindstroid.mindstroidgameapplication.utilities;

public class ScoreCalculator {

    private static final int maxScore = 100;
    private static final int maxTime = 300;

    public static int calculateScore(int timeTaken) {
        timeTaken = Math.min(timeTaken, maxTime);
        return ((int) maxScore * (1 - timeTaken / maxTime));
    }
}

package com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.service;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import com.mindstroid.mindstroidgameapplication.common.entity.LiveGame;
import com.mindstroid.mindstroidgameapplication.common.enums.GameTypes;
import com.mindstroid.mindstroidgameapplication.games.findindexsearching.dto.SearchingResult;
import com.mindstroid.mindstroidgameapplication.games.findindexsearching.entity.SearchingPerformace;
import com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.custom_sorting_algos.*;
import com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.entity.SortingPerformace;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Random;

@AllArgsConstructor
@Data
public class LiveRememberTheValueIndexGame extends LiveGame {

    private static final int sortedArraySize = 5000;
    private static final int maxValueRange = 1000000;
    private static final Random randomGenerator = new Random();
    private int[] numberArray;
    private int[] first20NumberArray;
    private long[] timeForAlgos;

    //sortingTimeForEachAlgo - 0->bubble sort, 1->insertion sort, 2->merge sort 3->radix sort, 4->shell sort, 5->quick sort, 6->tim sort

    public LiveRememberTheValueIndexGame(int id, Gamer gameOwner, GameTypes gameTypes, String gameId, LocalDateTime startedTime,
                                         LocalDateTime lastUpdatedTime, Boolean status) {
        super(id, gameOwner, gameTypes, gameId, startedTime, lastUpdatedTime, status);
        timeForAlgos = new long[7];
        run();
    }

    public void run() {
        numberArray = generateRandomValueArray(sortedArraySize);
        int[] sampleArray = numberArray;
        startSort(sampleArray);
        CustomBubbleSort.startBubbleSort(numberArray);
        generateArray();
    }

    private void generateArray() {
        for (int c = 0; c < 20; c++) {
            first20NumberArray[c] = numberArray[0];
        }
    }

    public SortingPerformace saveResultSearchingResult() {
        LiveGame liveGame = new LiveGame();
        liveGame.setId(super.id);
        liveGame.setGameTypes(super.gameTypes);
        liveGame.setGameOwner(super.gameOwner);
        liveGame.setGameId(super.gameId);
        liveGame.setStartedTime(super.startedTime);
        liveGame.setLastUpdatedTime(super.lastUpdatedTime);
        liveGame.setStatus(super.status);

        SortingPerformace sortingPerformace = new SortingPerformace();
        sortingPerformace.setLiveGame(liveGame);
        sortingPerformace.setTimeForBubbleSort(timeForAlgos[0]);
        sortingPerformace.setTimeForInsertionSort(timeForAlgos[1]);
        sortingPerformace.setTimeForMergeSort(timeForAlgos[2]);
        sortingPerformace.setTimeForRadixSort(timeForAlgos[3]);
        sortingPerformace.setTimeForShellSort(timeForAlgos[4]);
        sortingPerformace.setTimeForQuickSort(timeForAlgos[5]);
        sortingPerformace.setTimeForTimSort(timeForAlgos[6]);
        return sortingPerformace;
    }

    private void startSort(int[] arr) {
        long startedTime;
        long endTime;

        startedTime = System.nanoTime();
        CustomBubbleSort.startBubbleSort(arr);
        endTime = System.nanoTime();
        timeForAlgos[0] = endTime - startedTime;

        startedTime = System.nanoTime();
        CustomInsertionSort.startInsertionSort(arr);
        endTime = System.nanoTime();
        timeForAlgos[1] = endTime - startedTime;

        startedTime = System.nanoTime();
        CustomMergeSort.startMergeSort(arr);
        endTime = System.nanoTime();
        timeForAlgos[2] = endTime - startedTime;

        startedTime = System.nanoTime();
        CustomRadixSort.startRadixSort(arr);
        endTime = System.nanoTime();
        timeForAlgos[3] = endTime - startedTime;

        startedTime = System.nanoTime();
        CustomShellSort.startShellSort(arr);
        endTime = System.nanoTime();
        timeForAlgos[4] = endTime - startedTime;

        startedTime = System.nanoTime();
        CustomQuickSort.startQuickSort(arr);
        endTime = System.nanoTime();
        timeForAlgos[5] = endTime - startedTime;

//        startedTime = System.nanoTime();
//        sortUsingTimSort(arr);
//        endTime = System.nanoTime();
//        timeForAlgos[6] = endTime - startedTime;
    }

    private int[] generateRandomValueArray(int size) {
        int[] createdArray = new int[size];
        for (int i = 0; i < size; i++) {
            createdArray[i] = randomGenerator.nextInt(maxValueRange) + 1;
        }
        return createdArray;
    }

}

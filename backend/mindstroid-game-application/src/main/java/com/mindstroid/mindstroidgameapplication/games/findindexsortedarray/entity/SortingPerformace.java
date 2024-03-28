package com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.entity;

import com.mindstroid.mindstroidgameapplication.common.entity.LiveGame;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SortingPerformace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = LiveGame.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "game_id")
    private LiveGame liveGame;
    private Long timeForBubbleSort;
    private Long timeForInsertionSort;
    private Long timeForMergeSort;
    private Long timeForRadixSort;
    private Long timeForQuickSort;
    private Long timeForTimSort;
    private Long timeForShellSort;
}

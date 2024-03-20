package com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.entity;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import com.mindstroid.mindstroidgameapplication.common.entity.LiveGame;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NQueenPuzzleAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = LiveGame.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "game_id")
    private LiveGame liveGame;
    @ManyToOne(targetEntity = Gamer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "gamer_id")
    private Gamer gamer;
    private LocalDateTime answeredTime;
    private String answer;
}

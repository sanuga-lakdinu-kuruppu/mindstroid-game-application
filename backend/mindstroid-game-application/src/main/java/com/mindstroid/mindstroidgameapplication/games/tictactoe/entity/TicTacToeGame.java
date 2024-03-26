package com.mindstroid.mindstroidgameapplication.games.tictactoe.entity;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import com.mindstroid.mindstroidgameapplication.common.entity.LiveGame;
import com.mindstroid.mindstroidgameapplication.common.enums.GameTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class TicTacToeGame extends LiveGame {

    @Column(nullable = false)
    private Boolean winStatus;

    @Column(nullable = true)
    private String gameResult;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Gamer player;

    public TicTacToeGame() {
    }

    public TicTacToeGame(int id, GameTypes gameTypes, String gameId, LocalDateTime startedTime, LocalDateTime lastUpdatedTime, Boolean status, Gamer gameOwner, Boolean winStatus, String gameResult) {
        super(id, gameTypes, gameId, startedTime, lastUpdatedTime, status, gameOwner);
        this.winStatus = winStatus;
        this.gameResult = gameResult;
    }

    @Enumerated(EnumType.STRING)
    public GameTypes getGameTypes() {
        return super.getGameTypes();
    }

    @Override
    public void setGameOwner(Gamer gameOwner) {
        if (gameOwner == null) {
            throw new IllegalArgumentException("Game owner cannot be null");
        }
        super.setGameOwner(gameOwner);
    }

    @OneToOne(mappedBy = "ticTacToeGame", cascade = CascadeType.ALL)
    @JoinColumn(name = "gameOwner")
    public Gamer getGameOwner() {
        return super.getGameOwner();
    }
}
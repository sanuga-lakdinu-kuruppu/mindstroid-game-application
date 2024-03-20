package com.mindstroid.mindstroidgameapplication.common.entity;

import com.mindstroid.mindstroidgameapplication.common.enums.GameTypes;
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
public class LiveGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private GameTypes gameTypes;
    private String gameId;
    private LocalDateTime startedTime;
    private LocalDateTime lastUpdatedTime;
    private Boolean status;
    @OneToOne(targetEntity = Gamer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "gamer_id")
    private Gamer gameOwner;
}

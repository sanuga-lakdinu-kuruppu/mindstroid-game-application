package com.mindstroid.mindstroidgameapplication.common.repository;

import com.mindstroid.mindstroidgameapplication.common.entity.LiveGame;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<LiveGame, Integer> {

    Optional<LiveGame> findByGameId(String gameId);
}

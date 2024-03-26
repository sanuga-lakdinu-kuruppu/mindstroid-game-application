package com.mindstroid.mindstroidgameapplication.games.tictactoe.repository;

import com.mindstroid.mindstroidgameapplication.games.tictactoe.entity.TicTacToeGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicTacToeGameRepository extends JpaRepository<TicTacToeGame, Integer> {
}

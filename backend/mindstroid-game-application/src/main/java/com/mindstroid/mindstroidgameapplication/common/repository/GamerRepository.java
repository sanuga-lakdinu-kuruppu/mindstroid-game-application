package com.mindstroid.mindstroidgameapplication.common.repository;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Integer> {
}

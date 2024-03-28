package com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.repository;

import com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.entity.SortingPerformace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortingPerformanceRepository extends JpaRepository<SortingPerformace, Integer> {
}

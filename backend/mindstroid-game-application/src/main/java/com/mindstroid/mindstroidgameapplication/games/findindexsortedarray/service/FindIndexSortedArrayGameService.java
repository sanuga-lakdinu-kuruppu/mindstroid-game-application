package com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.service;

import com.mindstroid.mindstroidgameapplication.common.entity.Gamer;
import com.mindstroid.mindstroidgameapplication.common.entity.LiveGame;
import com.mindstroid.mindstroidgameapplication.common.enums.GameTypes;
import com.mindstroid.mindstroidgameapplication.common.repository.GameRepository;
import com.mindstroid.mindstroidgameapplication.common.repository.GamerRepository;
import com.mindstroid.mindstroidgameapplication.games.findindexsearching.dto.EachRoundResponse;
import com.mindstroid.mindstroidgameapplication.games.findindexsearching.service.LivePredictTheValueIndexGame;
import com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.dto.GameRondReponse;
import com.mindstroid.mindstroidgameapplication.games.findindexsortedarray.repository.SortingPerformanceRepository;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.dto.CreatedNQueenPuzzleGameResponse;
import com.mindstroid.mindstroidgameapplication.games.nqueenpuzzle.service.LiveNQueenPuzzleGame;
import com.mindstroid.mindstroidgameapplication.utilities.GameIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindIndexSortedArrayGameService {

    private static Logger audit = LogManager.getLogger("audit-log");
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GamerRepository gamerRepository;

    @Autowired
    private SortingPerformanceRepository sortingPerformanceRepository;
    private static final List<LiveRememberTheValueIndexGame> liveRememberTheValueIndexGames = new ArrayList<>();

    public ResponseEntity<GameRondReponse> createGame(String gamerName) {

        try {
            if (gamerName == null) {
                audit.info("game,remember-value-index,controller-/create,request,error,bad request");
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            LocalDateTime now = LocalDateTime.now();

            String gameId = GameIdGenerator.generateGameId(5);
            String gamerId = GameIdGenerator.generateGameId(10);

            //creation of gamer obj
            Gamer gamer = new Gamer();
            gamer.setName(gamerName);
            gamer.setGamerId(gamerId);
            var createdGamer = gamerRepository.save(gamer);

            //creation of game obj
            LiveGame liveGame = new LiveGame();
            liveGame.setGameTypes(GameTypes.INDEX_FINDER_SEARCHING);
            liveGame.setGameOwner(gamer);
            liveGame.setGameId(gameId);
            liveGame.setStartedTime(now);
            liveGame.setLastUpdatedTime(now);
            liveGame.setStatus(true);
            var createdGame = gameRepository.save(liveGame);

            LiveRememberTheValueIndexGame liveRememberTheValueIndexGame = new LiveRememberTheValueIndexGame(
                    createdGame.getId(), createdGamer, createdGame.getGameTypes(), createdGame.getGameId(),
                    createdGame.getStartedTime(), createdGame.getLastUpdatedTime(), createdGame.getStatus()
            );
            var obj = liveRememberTheValueIndexGame.saveResultSearchingResult();
            liveRememberTheValueIndexGames.add(liveRememberTheValueIndexGame);

            sortingPerformanceRepository.save(obj);

            HttpHeaders headers = new HttpHeaders();
            headers.add("game-id", String.valueOf(liveRememberTheValueIndexGame.getGameId()));

            audit.info("game,remember-value-index,controller-/create,request,created_game," + gameId + ",success,ok");
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(GameRondReponse.builder()
                    .valueArr(liveRememberTheValueIndexGame.getFirst20NumberArray())
                    .build());
        } catch (Exception e) {
            audit.info("game,remember-value-index,controller-/create,request,error,exception," + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

}

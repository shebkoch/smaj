package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.*;
import com.ogont.smaj.service.*;
import com.ogont.smaj.util.Pair;
import com.ogont.smaj.util.PlacedList;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static java.util.Comparator.comparingInt;

@Component
public class FullMatchService implements IFullMatchService {
    public static final int PLACE_DEAL = 50;
    public static final float FACTION_SCORE_MULTIPLIER = -0.2f;
    public static final float FACTION_COMBO_MULTIPLIER = -0.5f;
    public static final int SCORE_MULTIPLIER = 2;
    public static final float DURATION_MULTIPLIER = 0.015f;
    @Resource
    IMatchService matchService;
    @Resource
    IPlayerResultService playerResultService;
    @Resource
    IFactionService factionService;
    @Resource
    IFactionComboService factionComboService;
    @Resource
    PlayerService playerService;

    @Override
    public FullMatch save(FullMatch fullMatch, Boolean isEnd){
        matchService.save(fullMatch.getMatchEntity());
        setMatchId(fullMatch);
        playerResultService.saveAll(fullMatch.getPlayerResultEntities());
        if(isEnd) endSave(fullMatch);
        return fullMatch;
    }
    private FullMatch endSave(FullMatch fullMatch){
        List<PlayerResultEntity> results = fullMatch.getPlayerResultEntities();
        results = playerResultService.refresh(results);

        PlayerResultEntity winner = getWinner(fullMatch);

        for(PlayerResultEntity playerResultEntity : results){
            FactionEntity factionEntity1 = playerResultEntity.getFactionEntity1();
            FactionEntity factionEntity2 = playerResultEntity.getFactionEntity2();

            boolean isWinner = winner.equals(playerResultEntity);
            int size = results.size();
            factionService.computeScoreAndSave(factionEntity1, isWinner, size);
            factionService.computeScoreAndSave(factionEntity2, isWinner, size);

            factionComboService.computeScoreAndSave(factionEntity1.getId(),factionEntity2.getId(), isWinner, size);

            PlayerEntity playerEntity = playerResultEntity.getPlayerEntity();
            playerEntity.setMatchCount(playerEntity.getMatchCount() + 1);
            if(isWinner){
                playerEntity.setWinCount(playerEntity.getWinCount() + 1);
            }
            playerService.save(playerEntity);
        }
        results = playerResultService.refresh(results);
        computeMmr(fullMatch);
        playerResultService.saveAll(fullMatch.getPlayerResultEntities());
        return fullMatch;
    }
    @Override
    public void computeMmr(FullMatch match){
        List<PlayerResultEntity> playerResultEntities = match.getPlayerResultEntities();

        PlacedList<PlayerResultEntity> results = getPlacedList(playerResultEntities);
        int maxScore = results.maxScore();

        for(Pair<Integer, ArrayList<PlayerResultEntity>> pair : results){
            Integer place = pair.getKey();
            for(PlayerResultEntity result: pair.getValue()) {
                FactionEntity factionEntity1 = result.getFactionEntity1();
                FactionEntity factionEntity2 = result.getFactionEntity2();
                FactionComboEntity factionComboEntity = factionComboService.findByFaction1IdAndFaction2Id(factionEntity1.getId(), factionEntity2.getId());

                float size = (float)  playerResultEntities.size() + 1;
                float mmrChange = PLACE_DEAL / 2.0f - PLACE_DEAL / size * place;
                if(result.getWinner()) mmrChange += place * PLACE_DEAL / size;
                mmrChange += factionByScoreMmr(factionEntity1.getScore());
                mmrChange += factionByScoreMmr(factionEntity2.getScore());
                mmrChange += factionComboByScoreMmr(factionComboEntity.getScore());
                mmrChange += ((float) result.getScore() / (float) maxScore * SCORE_MULTIPLIER);
                float durationChange = match.duration() * DURATION_MULTIPLIER;
                if (mmrChange > 0) mmrChange -= durationChange;
                else mmrChange += durationChange;

                result.setMmrChange((int) mmrChange);

                PlayerEntity playerEntity = result.getPlayerEntity();
                playerEntity.setMmr(playerEntity.getMmr() + result.getMmrChange());
                playerService.save(playerEntity);
            }
        }
    }
    private float factionByScoreMmr(BigDecimal score){
        float scoreF = score.floatValue();
        return scoreF * FACTION_SCORE_MULTIPLIER;
    }
    private float factionComboByScoreMmr(BigDecimal score){
        float scoreF = score.floatValue();
        return scoreF * FACTION_COMBO_MULTIPLIER;
    }
    private PlayerResultEntity getWinner(FullMatch fullMatch){
        List<PlayerResultEntity> results = fullMatch.getPlayerResultEntities();
        for(PlayerResultEntity entity : results){
            if(entity.getWinner()) return entity;
        }

        results.sort(comparingInt(PlayerResultEntity::getScore));
        return results.get(results.size() -1);
    }
    private void setMatchId(FullMatch fullMatch){
        for (PlayerResultEntity playerResultEntity : fullMatch.getPlayerResultEntities()){
            playerResultEntity.setMatchId(fullMatch.getMatchEntity().getId());
        }
    }

    private PlacedList<PlayerResultEntity> getPlacedList(List<PlayerResultEntity> playerResultEntities){
        PlacedList<PlayerResultEntity> placedList = new PlacedList<>();
        for (PlayerResultEntity entity : playerResultEntities){
            placedList.add(entity.getScore(), entity);
        }
        return placedList;
    }
}

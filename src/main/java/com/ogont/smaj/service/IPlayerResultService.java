package com.ogont.smaj.service;

import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.model.PlayerResultEntity;
import com.ogont.smaj.util.Pair;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPlayerResultService extends IService<PlayerResultEntity,Integer> {
    List<PlayerResultEntity> refresh(List<PlayerResultEntity> resultEntities);
    PlayerResultEntity getLast(Integer id);

    Map<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> getPlayerFactionStat(List<PlayerEntity> players);

    FactionEntity getBestFaction(PlayerEntity playerEntity);
    List<PlayerResultEntity> findAllByPlayerId(Integer id);
    List<PlayerResultEntity> findAllByMatchId(Integer id);
}

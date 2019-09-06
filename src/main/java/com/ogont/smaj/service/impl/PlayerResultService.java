package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.model.PlayerResultEntity;
import com.ogont.smaj.repository.IPlayerResultRepository;
import com.ogont.smaj.service.IFactionService;
import com.ogont.smaj.service.IPlayerResultService;
import com.ogont.smaj.service.IPlayerService;
import com.ogont.smaj.util.Pair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class PlayerResultService implements IPlayerResultService {
    @Resource
    IPlayerResultRepository repository;

    @Resource
    IPlayerService playerService;
    @Resource
    IFactionService factionService;

    @Override
    public List<PlayerResultEntity> refresh(List<PlayerResultEntity> resultEntities) {
        for (PlayerResultEntity resultEntity : resultEntities) {
            resultEntity.setPlayerEntity(playerService.findById(resultEntity.getPlayerId()).get());

            resultEntity.setFactionEntity1(factionService.findById(resultEntity.getFaction1Id()).get());
            resultEntity.setFactionEntity2(factionService.findById(resultEntity.getFaction2Id()).get());
        }
        return resultEntities;
    }

    @Override
    public PlayerResultEntity getLast(Integer id) {
        List<PlayerResultEntity> entities = new ArrayList<>(repository.findAllByPlayerId(id));

        entities.sort(Comparator.comparing(x -> x.getMatchEntity().getEtime()));
        return entities.get(entities.size()-1);
    }

    @Override
    public Map<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> getPlayerFactionStat(List<PlayerEntity> players) {
        Map<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> resMap = new HashMap<>();
        for (PlayerEntity player : players) {
            Map<FactionEntity, Pair<Integer, Integer>> factionMap;
            if (resMap.containsKey(player)) factionMap = resMap.get(player);
            else {
                factionMap = new HashMap<>();
                resMap.put(player, factionMap);
            }

            List<PlayerResultEntity> results = repository.findAllByPlayerId(player.getId());

            for (PlayerResultEntity result : results) {
                FactionEntity factionEntity1 = result.getFactionEntity1();
                FactionEntity factionEntity2 = result.getFactionEntity2();

                if (!factionMap.containsKey(factionEntity1))
                    factionMap.put(factionEntity1, new Pair<>(0,0));
                if (!factionMap.containsKey(factionEntity2))
                    factionMap.put(factionEntity2, new Pair<>(0,0));

                //TODO:
                factionMap.put(factionEntity1, factionMap.get(factionEntity1) + 1);
                factionMap.put(factionEntity2, factionMap.get(factionEntity2) + 1);
            }
        }
        return resMap;
    }

    @Override
    public CrudRepository<PlayerResultEntity, Integer> getRepository() {
        return repository;
    }
}

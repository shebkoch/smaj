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

import static java.util.Collections.singletonList;

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
        if(entities.size() > 0)
            return entities.get(entities.size()-1);
        else return null;
    }

    /**
     * @return faction, pair<matches,win>
     */
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
                Pair<Integer, Integer> pair1 = factionMap.get(factionEntity1);
                Pair<Integer, Integer> pair2= factionMap.get(factionEntity2);

                if(result.getWinner()){
                    pair1.setValue(pair1.getValue()+1);
                    pair2.setValue(pair2.getValue()+1);
                }
                pair1.setKey(pair1.getKey()+1);
                pair2.setKey(pair2.getKey()+1);
                factionMap.put(factionEntity1, pair1);
                factionMap.put(factionEntity2, pair2);
            }
        }
        return resMap;
    }

    @Override
    public FactionEntity getBestFaction(PlayerEntity playerEntity) {
        Map<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> playerFactionStat = getPlayerFactionStat(singletonList(playerEntity));
        Map<FactionEntity, Pair<Integer, Integer>> factionEntityPairMap = playerFactionStat.get(playerEntity);
        Pair<Double,FactionEntity> result = new Pair<>(-1D, null);
        for(Map.Entry<FactionEntity, Pair<Integer, Integer>> entry : factionEntityPairMap.entrySet()){
            FactionEntity factionEntity = entry.getKey();
            Pair<Integer, Integer> pair = entry.getValue();

            Double score = (double) pair.getValue() / pair.getKey();
            if(score > result.getKey()){
                result.setKey(score);
                result.setValue(factionEntity);
            }
        }
        return result.getValue();
    }

    @Override
    public List<PlayerResultEntity> findAllByPlayerId(Integer id) {
        return repository.findAllByPlayerId(id);
    }

    @Override
    public CrudRepository<PlayerResultEntity, Integer> getRepository() {
        return repository;
    }
}

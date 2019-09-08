package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.DistributeData;
import com.ogont.smaj.model.FactionComboEntity;
import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.service.IFactionDistributorService;
import com.ogont.smaj.service.IFactionService;
import com.ogont.smaj.service.IPlayerResultService;
import com.ogont.smaj.util.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class FactionDistributorService implements IFactionDistributorService {

    public static final double STEP_MIN = 0.1d;
    @Resource
    IFactionService factionService;
    @Resource
    IPlayerResultService playerResultService;

    public Map<PlayerEntity, FactionComboEntity> distribute(List<PlayerEntity> players){
        DistributeData data = new DistributeData();
        data.setFactions(factionService.getAll());
        data.setPlayers(players);
        return distribute(data);
    }
//s =10+0+4+6=20
//c = ni/ s;
//ch = 100 / n;
//ch /=  c
//1:1/2=  8
//2:1/20 = 80
//3:1/5 = 20
//4:3/10 = 13
    @Override
    public Map<PlayerEntity, FactionComboEntity> distribute(DistributeData data){
        Map<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> playerFactionStat = playerResultService.getPlayerFactionStat(data.getPlayers());
        Map<PlayerEntity, FactionComboEntity> result = new HashMap<>();
        for(Map.Entry<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> entry : playerFactionStat.entrySet()){
            Map<FactionEntity, Double> chances = new HashMap<>();
            int size = data.getFactions().size();
            Double sum = 0d;
            Integer max = 0;
            for (FactionEntity factionEntity : data.getFactions()) {
                Integer matchCount = factionEntity.getMatchCount() + getFactionMatch(factionEntity, entry.getValue());
                sum += matchCount;
                if(matchCount >max) max = matchCount;
            }
            max++;
            for (FactionEntity factionEntity : data.getFactions()) {
                Integer matchCount = factionEntity.getMatchCount() + getFactionMatch(factionEntity, entry.getValue());
                int part = max - matchCount;
                Double chance = part * 100 / sum;
                chances.put(factionEntity, chance);
            }
            FactionEntity factionEntity1 = factionGet(chances);
            chances.remove(factionEntity1);
            FactionEntity factionEntity2 = factionGet(chances);
            chances.remove(factionEntity2);

            data.getFactions().remove(factionEntity1);
            data.getFactions().remove(factionEntity2);

            FactionComboEntity factionComboEntity = new FactionComboEntity();
            factionComboEntity.setFactionEntity1(factionEntity1);
            factionComboEntity.setFactionEntity2(factionEntity2);
            result.put(entry.getKey(), factionComboEntity);
        }

        return result;
    }

    private Integer getFactionMatch(FactionEntity factionEntity, Map<FactionEntity, Pair<Integer, Integer>> map) {
        for (Map.Entry<FactionEntity, Pair<Integer, Integer>> entry : map.entrySet()){
            if(Objects.equals(entry.getKey().getId(), factionEntity.getId())){
                return entry.getValue().getKey();
            }
        }
        return 0;
    }

    private FactionEntity factionGet(Map<FactionEntity, Double> res){
        Double chanceSum = 0d;
        for(Double value: res.values()){
            chanceSum+=value;
        }
        Random random = new Random();
        double chance = random.nextDouble() * chanceSum;
        Double current = 0d;
        for(Map.Entry<FactionEntity, Double> resEntry: res.entrySet()){
            current += resEntry.getValue();
            if (chance <= current){
                return resEntry.getKey();
            }
        }
        return null;
    }
}



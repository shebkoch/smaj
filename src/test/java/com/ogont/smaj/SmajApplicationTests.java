package com.ogont.smaj;

import com.ogont.smaj.model.*;
import com.ogont.smaj.service.impl.FactionDistributorService;
import com.ogont.smaj.service.impl.FactionService;
import com.ogont.smaj.service.impl.PlayerResultService;
import com.ogont.smaj.service.impl.PlayerService;
import com.ogont.smaj.util.Pair;
import com.ogont.smaj.util.PlacedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmajApplicationTests {
    Logger logger = LogManager.getLogger(this.getClass());
    @Test
    public void test1() {
        PlacedList<PlayerResultEntity> list = new PlacedList<>();

        PlayerResultEntity entity = new PlayerResultEntity();
        entity.setScore(13);
        PlayerResultEntity entity1 = new PlayerResultEntity();
        entity1.setScore(14);
        PlayerResultEntity entity2 = new PlayerResultEntity();
        entity2.setScore(15);
        PlayerResultEntity entity3 = new PlayerResultEntity();
        entity3.setScore(16);

        list.add(entity.getScore(), entity);
        list.add(entity1.getScore(), entity1);
        list.add(entity2.getScore(), entity2);
        list.add(entity3.getScore(), entity3);

        for(Pair<Integer, ArrayList<PlayerResultEntity>> entry :list){
            logger.info(String.format("%d %s %n", entry.getKey(), entry.getValue().get(0).getScore()));
        }
    }
    @Test
    public void test2() {
        PlacedList<PlayerResultEntity> list = new PlacedList<>();

        PlayerResultEntity entity = new PlayerResultEntity();
        entity.setScore(13);
        PlayerResultEntity entity1 = new PlayerResultEntity();
        entity1.setScore(13);
        PlayerResultEntity entity2 = new PlayerResultEntity();
        entity2.setScore(15);
        PlayerResultEntity entity3 = new PlayerResultEntity();
        entity3.setScore(16);

        list.add(entity.getScore(), entity);
        list.add(entity1.getScore(), entity1);
        list.add(entity2.getScore(), entity2);
        list.add(entity3.getScore(), entity3);

        for(Pair<Integer, ArrayList<PlayerResultEntity>> entry :list){
            logger.info(String.format("%d %s %n", entry.getKey(), entry.getValue().get(0).getScore()));
        }
    }
    @Test
    public void test3() {
        PlacedList<PlayerResultEntity> list = new PlacedList<>();

        PlayerResultEntity entity = new PlayerResultEntity();
        entity.setScore(13);
        PlayerResultEntity entity1 = new PlayerResultEntity();
        entity1.setScore(14);
        PlayerResultEntity entity2 = new PlayerResultEntity();
        entity2.setScore(14);
        PlayerResultEntity entity3 = new PlayerResultEntity();
        entity3.setScore(16);

        list.add(entity.getScore(), entity);
        list.add(entity1.getScore(), entity1);
        list.add(entity2.getScore(), entity2);
        list.add(entity3.getScore(), entity3);

        for(Pair<Integer, ArrayList<PlayerResultEntity>> entry :list){
            logger.info(String.format("%d %s %n", entry.getKey(), entry.getValue().get(0).getScore()));
        }
    }
    @Test
    public void test4() {
        PlacedList<PlayerResultEntity> list = new PlacedList<>();

        PlayerResultEntity entity = new PlayerResultEntity();
        entity.setScore(13);
        PlayerResultEntity entity1 = new PlayerResultEntity();
        entity1.setScore(14);
        PlayerResultEntity entity2 = new PlayerResultEntity();
        entity2.setScore(15);
        PlayerResultEntity entity3 = new PlayerResultEntity();
        entity3.setScore(15);

        list.add(entity.getScore(), entity);
        list.add(entity1.getScore(), entity1);
        list.add(entity2.getScore(), entity2);
        list.add(entity3.getScore(), entity3);

        for(Pair<Integer, ArrayList<PlayerResultEntity>> entry :list){
            logger.info(String.format("%d %s %n", entry.getKey(), entry.getValue().get(0).getScore()));
        }
    }


    @Resource
    PlayerService playerService;
    @Resource
    PlayerResultService playerResultService;
    @Test
    public void test5(){
        List<PlayerEntity> entities = playerService.getAll();
        Map<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> playerFactionStat = playerResultService.getPlayerFactionStat(entities);
    }
    @Resource
    FactionDistributorService factionDistributorService;
    @Resource
    FactionService factionService;
    @Test
    public void test6(){
        DistributeData data = new DistributeData();
        PlayerEntity entity = playerService.findById(6).get();
        PlayerEntity entity2 = playerService.findById(1).get();
        data.setPlayers(Arrays.asList(entity, entity2));
        List<FactionEntity> factionEntities = factionService.getAll();

        Map<FactionEntity, Integer> count = new HashMap<>();
        for(FactionEntity factionEntity : factionEntities){
            count.put(factionEntity, 0);
        }
        for (int i = 0; i < 10000; i++) {
            data.setFactions(new ArrayList<>(factionEntities));
            Map<PlayerEntity, FactionComboEntity> map = factionDistributorService.distribute(data);
            Integer cnt1 = count.get(map.get(entity).getFactionEntity1());
            Integer cnt2 = count.get(map.get(entity).getFactionEntity2());
            Integer cnt3 = count.get(map.get(entity2).getFactionEntity1());
            Integer cnt4 = count.get(map.get(entity2).getFactionEntity2());
            count.put(map.get(entity).getFactionEntity1(), cnt1+1);
            count.put(map.get(entity).getFactionEntity2(), cnt2+1);
            count.put(map.get(entity2).getFactionEntity1(), cnt3+1);
            count.put(map.get(entity2).getFactionEntity2(), cnt4+1);
        }
        Map<PlayerEntity, Map<FactionEntity, Pair<Integer, Integer>>> playerFactionStat = playerResultService.getPlayerFactionStat(data.getPlayers());
        for (Map.Entry<FactionEntity, Integer> entry : count.entrySet()){
            Integer playerMatch = 0;
            Integer allMatch = 0;
            Pair<Integer, Integer> pair = playerFactionStat.get(entity).get(entry.getKey());
            if(pair != null) playerMatch = pair.getKey();
            Integer matchCount = entry.getKey().getMatchCount();
            if(matchCount != null) allMatch = matchCount;
            System.out.println(entry.getKey().getName() + "\t" + entry.getValue() + "\t" + allMatch);
        }


    }
}

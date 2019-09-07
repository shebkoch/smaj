package com.ogont.smaj;

import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.model.PlayerResultEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}

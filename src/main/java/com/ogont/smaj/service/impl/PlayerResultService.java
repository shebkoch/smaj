package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.model.PlayerResultEntity;
import com.ogont.smaj.repository.IPlayerResultRepository;
import com.ogont.smaj.service.IFactionService;
import com.ogont.smaj.service.IMatchService;
import com.ogont.smaj.service.IPlayerResultService;
import com.ogont.smaj.service.IPlayerService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerResultService implements IPlayerResultService {
    @Resource
    IPlayerResultRepository repository;

    @Resource
    IPlayerService playerService;
    @Resource
    IFactionService factionService;

    @Override
    public List<PlayerResultEntity> refresh(List<PlayerResultEntity> resultEntities){
        for (PlayerResultEntity resultEntity: resultEntities) {
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
        return entities.get(0);
    }

    @Override
    public CrudRepository<PlayerResultEntity, Integer> getRepository() {
        return repository;
    }
}

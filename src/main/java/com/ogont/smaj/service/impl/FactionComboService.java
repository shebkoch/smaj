package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.FactionComboEntity;
import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.repository.IFactionComboRepository;
import com.ogont.smaj.service.IFactionComboService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class FactionComboService implements IFactionComboService {
    @Resource
    IFactionComboRepository repository;
    @Override
    public CrudRepository<FactionComboEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    public FactionComboEntity findByFaction1IdAndFaction2Id(Integer faction1Id, Integer faction2Id) {
        return repository.findByFaction1IdAndFaction2Id(faction1Id, faction2Id);
    }
    public FactionComboEntity findOrCreate(Integer faction1Id, Integer faction2Id){
        FactionComboEntity factionComboEntity =findByFaction1IdAndFaction2Id(faction1Id, faction2Id);
        if(factionComboEntity == null){
            factionComboEntity = new FactionComboEntity(faction1Id,faction2Id);
            save(factionComboEntity);
        }
        return factionComboEntity;
    }
    @Override
    public void computeScoreAndSave(Integer faction1id, Integer faction2Id, Boolean win, Integer playersCount) {
        FactionComboEntity factionComboEntity = findOrCreate(faction1id, faction2Id);
        factionComboEntity.setMatchCount(factionComboEntity.getMatchCount()+1);
        BigDecimal change = null;
        if (win){
            factionComboEntity.setWinCount(factionComboEntity.getWinCount()+1);
            change = BigDecimal.valueOf((float)(playersCount-1)/(float)playersCount);
        }else {
            change = BigDecimal.valueOf(-1.0f/(float)playersCount);
        }
        factionComboEntity.setScore(factionComboEntity.getScore().add(change));

        save(factionComboEntity);
    }
}

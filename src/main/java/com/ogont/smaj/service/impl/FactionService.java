package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.FactionComboEntity;
import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.repository.IFactionRepository;
import com.ogont.smaj.service.IFactionService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class FactionService implements IFactionService {
    @Resource
    IFactionRepository repository;

    @Override
    public CrudRepository<FactionEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    public void computeScoreAndSave(FactionEntity factionEntity, Boolean win, Integer playersCount) {
            computeScoreAndSave(factionEntity.getId(), win, playersCount);
    }

    @Override
    public void computeScoreAndSave(Integer id, Boolean win, Integer playersCount) {
        FactionEntity factionEntity = findById(id).get();
        factionEntity.setMatchCount(factionEntity.getMatchCount()+1);
        BigDecimal change = null;
        if (win){
            factionEntity.setWinCount(factionEntity.getWinCount()+1);
            change = BigDecimal.valueOf((float)(playersCount-1)/(float)playersCount);
        }else {
            change = BigDecimal.valueOf(-1.0f/(float)playersCount);
        }
        factionEntity.setScore(factionEntity.getScore().add(change));

        save(factionEntity);
    }
}

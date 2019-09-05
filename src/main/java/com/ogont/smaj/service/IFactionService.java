package com.ogont.smaj.service;

import com.ogont.smaj.model.FactionEntity;

public interface IFactionService extends IService<FactionEntity,Integer> {
    void computeScoreAndSave(FactionEntity factionEntity, Boolean win, Integer playersCount);

    void computeScoreAndSave(Integer id, Boolean win, Integer playersCount);
}

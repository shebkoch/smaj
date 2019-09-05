package com.ogont.smaj.service;

import com.ogont.smaj.model.FactionComboEntity;

public interface IFactionComboService extends IService<FactionComboEntity,Integer>  {
    FactionComboEntity findByFaction1IdAndFaction2Id(Integer faction1Id, Integer faction2Id);

    void computeScoreAndSave(Integer faction1id, Integer faction2Id, Boolean win, Integer playersCount);
}

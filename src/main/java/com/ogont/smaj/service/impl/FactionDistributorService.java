package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.FactionComboEntity;
import com.ogont.smaj.model.FactionEntity;
import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.service.IFactionDistributorService;
import com.ogont.smaj.service.IFactionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class FactionDistributorService implements IFactionDistributorService {

    @Resource
    IFactionService factionService;

    public Map<PlayerEntity, FactionComboEntity> distribute(List<PlayerEntity> players){
        return distribute(players, factionService.getAll());
    }

    public Map<PlayerEntity, FactionComboEntity> distribute(List<PlayerEntity> players, List<FactionEntity> factions){
        return null;
    }
}

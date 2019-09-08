package com.ogont.smaj.service;

import com.ogont.smaj.model.DistributeData;
import com.ogont.smaj.model.FactionComboEntity;
import com.ogont.smaj.model.PlayerEntity;

import java.util.Map;

public interface IFactionDistributorService {
    //s =10+0+4+6=20
    //c = ni/ s;
    //ch = 100 / n;
    //ch /=  c
    //1:1/2=  8
    //2:1/20 = 80
    //3:1/5 = 20
    //4:3/10 = 13
    Map<PlayerEntity, FactionComboEntity> distribute(DistributeData data);
}

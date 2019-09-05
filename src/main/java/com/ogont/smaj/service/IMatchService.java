package com.ogont.smaj.service;

import com.ogont.smaj.model.MatchEntity;

public interface IMatchService extends IService<MatchEntity,Integer> {
    public MatchEntity getLast();
}

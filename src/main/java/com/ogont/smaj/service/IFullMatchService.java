package com.ogont.smaj.service;

import com.ogont.smaj.model.FullMatch;

public interface IFullMatchService {
    FullMatch save(FullMatch fullMatch, Boolean isEnd);
    FullMatch getByMatchId(Integer id);
    void computeMmr(FullMatch match);
}

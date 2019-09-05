package com.ogont.smaj.service;

import com.ogont.smaj.model.FullMatch;

public interface IFullMatchService {
    FullMatch save(FullMatch fullMatch, Boolean isEnd);

    void computeMmr(FullMatch match);
}

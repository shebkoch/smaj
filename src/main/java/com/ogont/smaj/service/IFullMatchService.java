package com.ogont.smaj.service;

import com.ogont.smaj.model.FullMatch;

import java.util.List;

public interface IFullMatchService {
    FullMatch save(FullMatch fullMatch, Boolean isEnd);
    FullMatch getByMatchId(Integer id);

    List<FullMatch> getAll();

    void computeMmr(FullMatch match);
}

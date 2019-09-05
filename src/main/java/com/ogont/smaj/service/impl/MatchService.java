package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.MatchEntity;
import com.ogont.smaj.repository.IMatchRepository;
import com.ogont.smaj.service.IMatchService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class MatchService implements IMatchService {
    @Resource
    IMatchRepository repository;

    @Override
    public CrudRepository<MatchEntity, Integer> getRepository() {
        return repository;
    }

    @Override
    public MatchEntity getLast() {
        return repository.findTopByRating(true);
    }
}

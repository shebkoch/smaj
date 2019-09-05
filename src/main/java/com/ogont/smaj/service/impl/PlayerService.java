package com.ogont.smaj.service.impl;

import com.ogont.smaj.model.PlayerEntity;
import com.ogont.smaj.repository.IPlayerRepository;
import com.ogont.smaj.service.IPlayerService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component

public class PlayerService implements IPlayerService {
    @Resource
    IPlayerRepository repository;
    @Override
    public CrudRepository<PlayerEntity, Integer> getRepository() {
        return repository;
    }
    public List<PlayerEntity> findAllByIdIn(List<Integer> ids){
        return repository.findAllByIdIn(ids);
    }
    @Override
    public Integer getAverageMmr() {
        return repository.getAverageMmr();
    }
}

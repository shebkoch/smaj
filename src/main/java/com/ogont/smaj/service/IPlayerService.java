package com.ogont.smaj.service;

import com.ogont.smaj.model.PlayerEntity;

import java.util.List;

public interface IPlayerService  extends IService<PlayerEntity,Integer> {
    Integer getAverageMmr();
    List<PlayerEntity> findAllByIdIn(List<Integer> ids);
}

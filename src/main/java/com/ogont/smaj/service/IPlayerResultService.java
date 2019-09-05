package com.ogont.smaj.service;

import com.ogont.smaj.model.PlayerResultEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPlayerResultService extends IService<PlayerResultEntity,Integer> {
    List<PlayerResultEntity> refresh(List<PlayerResultEntity> resultEntities);
    PlayerResultEntity getLast(Integer id);
}

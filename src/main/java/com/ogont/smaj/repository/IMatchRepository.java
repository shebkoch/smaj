package com.ogont.smaj.repository;

import com.ogont.smaj.model.MatchEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMatchRepository extends CrudRepository<MatchEntity, Integer> {
    MatchEntity findTopByRating(boolean isRating);
}

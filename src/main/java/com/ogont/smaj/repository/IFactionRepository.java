package com.ogont.smaj.repository;

import com.ogont.smaj.model.FactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFactionRepository extends CrudRepository<FactionEntity, Integer> {
}

package com.ogont.smaj.repository;

import com.ogont.smaj.model.FactionComboEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFactionComboRepository extends CrudRepository<FactionComboEntity, Integer> {
    FactionComboEntity findByFaction1IdAndFaction2Id(Integer faction1Id, Integer faction2Id);
}

package com.ogont.smaj.model;

import java.util.List;

public class DistributeData {
    private List<FactionEntity> factions;
    private List<PlayerEntity> players;

    public List<FactionEntity> getFactions() {
        return factions;
    }

    public void setFactions(List<FactionEntity> factions) {
        this.factions = factions;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }
}

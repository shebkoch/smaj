package com.ogont.smaj.model;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "player_result", schema = "public", catalog = "smaj")
public class PlayerResultEntity implements IGenericEntity<Integer>  {
    private Integer id;
    private Integer matchId;
    private Integer playerId;
    private Integer faction1Id;
    private Integer faction2Id;
    private Integer score;
    private Integer mmrChange;

    private MatchEntity matchEntity;
    private PlayerEntity playerEntity;
    private FactionEntity factionEntity1;
    private FactionEntity factionEntity2;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id", referencedColumnName = "id", insertable = false, updatable = false)
    public MatchEntity getMatchEntity() {
        return matchEntity;
    }

    public void setMatchEntity(MatchEntity matchEntity) {
        this.matchEntity = matchEntity;
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id", insertable = false, updatable = false)
    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faction1_id", referencedColumnName = "id", insertable = false, updatable = false)
    public FactionEntity getFactionEntity1() {
        return factionEntity1;
    }

    public void setFactionEntity1(FactionEntity factionEntity1) {
        this.factionEntity1 = factionEntity1;
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faction2_id", referencedColumnName = "id", insertable = false, updatable = false)
    public FactionEntity getFactionEntity2() {
        return factionEntity2;
    }

    public void setFactionEntity2(FactionEntity factionEntity2) {
        this.factionEntity2 = factionEntity2;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "match_id")
    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    @Basic
    @Column(name = "player_id")
    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "faction1_id")
    public Integer getFaction1Id() {
        return faction1Id;
    }

    public void setFaction1Id(Integer faction1Id) {
        this.faction1Id = faction1Id;
    }

    @Basic
    @Column(name = "faction2_id")
    public Integer getFaction2Id() {
        return faction2Id;
    }

    public void setFaction2Id(Integer faction2Id) {
        this.faction2Id = faction2Id;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "mmr_change")
    public Integer getMmrChange() {
        return mmrChange;
    }

    public void setMmrChange(Integer mmrChange) {
        this.mmrChange = mmrChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerResultEntity that = (PlayerResultEntity) o;

        if (id != that.id) return false;
        if (matchId != null ? !matchId.equals(that.matchId) : that.matchId != null) return false;
        if (playerId != null ? !playerId.equals(that.playerId) : that.playerId != null) return false;
        if (faction1Id != null ? !faction1Id.equals(that.faction1Id) : that.faction1Id != null) return false;
        if (faction2Id != null ? !faction2Id.equals(that.faction2Id) : that.faction2Id != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (mmrChange != null ? !mmrChange.equals(that.mmrChange) : that.mmrChange != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (matchId != null ? matchId.hashCode() : 0);
        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
        result = 31 * result + (faction1Id != null ? faction1Id.hashCode() : 0);
        result = 31 * result + (faction2Id != null ? faction2Id.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (mmrChange != null ? mmrChange.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlayerResultEntity{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", playerId=" + playerId +
                ", faction1Id=" + faction1Id +
                ", faction2Id=" + faction2Id +
                ", score=" + score +
                ", mmrChange=" + mmrChange +
                ", matchEntity=" + matchEntity +
                ", playerEntity=" + playerEntity +
                ", factionEntity1=" + factionEntity1 +
                ", factionEntity2=" + factionEntity2 +
                '}';
    }
}

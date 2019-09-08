package com.ogont.smaj.model;

import javax.persistence.*;

@Entity
@Table(name = "player", schema = "public", catalog = "smaj")
public class PlayerEntity implements IGenericEntity<Integer>  {
    private Integer id;
    private String name;
    private Integer matchCount;
    private Integer winCount;
    private Integer mmr;
    private Integer bestFaction;

    private FactionEntity bestFactionEntity;

    @OneToOne
    @JoinColumn(name = "best_faction", referencedColumnName = "id", insertable = false, updatable = false)
    public FactionEntity getBestFactionEntity() {
        return bestFactionEntity;
    }

    public void setBestFactionEntity(FactionEntity bestFactionEntity) {
        this.bestFactionEntity = bestFactionEntity;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "match_count")
    public Integer getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(Integer matchCount) {
        this.matchCount = matchCount;
    }

    @Basic
    @Column(name = "win_count")
    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    @Basic
    @Column(name = "mmr")
    public Integer getMmr() {
        return mmr;
    }

    public void setMmr(Integer mmr) {
        this.mmr = mmr;
    }

    @Basic
    @Column(name = "best_faction")
    public Integer getBestFaction() {
        return bestFaction;
    }

    public void setBestFaction(Integer bestFaction) {
        this.bestFaction = bestFaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEntity that = (PlayerEntity) o;

        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (matchCount != null ? !matchCount.equals(that.matchCount) : that.matchCount != null) return false;
        if (winCount != null ? !winCount.equals(that.winCount) : that.winCount != null) return false;
        if (mmr != null ? !mmr.equals(that.mmr) : that.mmr != null) return false;
        if (bestFaction != null ? !bestFaction.equals(that.bestFaction) : that.bestFaction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (matchCount != null ? matchCount.hashCode() : 0);
        result = 31 * result + (winCount != null ? winCount.hashCode() : 0);
        result = 31 * result + (mmr != null ? mmr.hashCode() : 0);
        result = 31 * result + (bestFaction != null ? bestFaction.hashCode() : 0);
        return result;
    }
}

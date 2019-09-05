package com.ogont.smaj.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "faction_combo", schema = "public", catalog = "smaj")
public class FactionComboEntity implements IGenericEntity<Integer>{
    private Integer id;
    private Integer matchCount;
    private Integer winCount;
    private BigDecimal score;
    private Integer faction1Id;
    private Integer faction2Id;

    private FactionEntity factionEntity1;
    private FactionEntity factionEntity2;

    public FactionComboEntity() {
    }

    public FactionComboEntity(Integer faction1Id, Integer faction2Id) {
        this.faction1Id = faction1Id;
        this.faction2Id = faction2Id;
        this.score = BigDecimal.ZERO;
        this.matchCount = 0;
        this.winCount = 0;
    }

    @OneToOne
    @JoinColumn(name = "faction1_id", referencedColumnName = "id", insertable = false, updatable = false)
    public FactionEntity getFactionEntity1() {
        return factionEntity1;
    }

    public void setFactionEntity1(FactionEntity factionEntity1) {
        this.factionEntity1 = factionEntity1;
    }

    @OneToOne
    @JoinColumn(name = "faction2_id", referencedColumnName = "id", insertable = false, updatable = false)
    public FactionEntity getFactionEntity2() {
        return factionEntity2;
    }

    public void setFactionEntity2(FactionEntity factionEntity2) {
        this.factionEntity2 = factionEntity2;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "score")
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FactionComboEntity that = (FactionComboEntity) o;

        if (id != that.id) return false;
        if (matchCount != null ? !matchCount.equals(that.matchCount) : that.matchCount != null) return false;
        if (winCount != null ? !winCount.equals(that.winCount) : that.winCount != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (matchCount != null ? matchCount.hashCode() : 0);
        result = 31 * result + (winCount != null ? winCount.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
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
}

package com.ogont.smaj.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "faction", schema = "public", catalog = "smaj")
public class FactionEntity implements IGenericEntity<Integer> {
    private Integer id;
    private String name;
    private Integer matchCount;
    private Integer winCount;
    private BigDecimal score;

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

        FactionEntity that = (FactionEntity) o;

        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (matchCount != null ? !matchCount.equals(that.matchCount) : that.matchCount != null) return false;
        if (winCount != null ? !winCount.equals(that.winCount) : that.winCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (matchCount != null ? matchCount.hashCode() : 0);
        result = 31 * result + (winCount != null ? winCount.hashCode() : 0);
        return result;
    }
}

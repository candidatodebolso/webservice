package br.com.candidatodebolso.webservice.persistence.model.pollintention;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;
import br.com.candidatodebolso.webservice.persistence.model.candidate.Candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Intention extends AbstractEntity {

    @Column(nullable = false)
    private Integer points;

    @ManyToOne
    private Candidate candidate;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}

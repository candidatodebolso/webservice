package br.com.candidatodebolso.webservice.persistence.model.pollintention;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PollIntention extends AbstractEntity {

    private LocalDate date;

    @ManyToOne
    private Researcher researcher;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Intention> intentions;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonIgnore
    public Researcher getResearcher() {
        return researcher;
    }

    public void setResearcher(Researcher researcher) {
        this.researcher = researcher;
    }

    public List<Intention> getIntentions() {
        return intentions;
    }

    public void setIntentions(List<Intention> intentions) {
        this.intentions = intentions;
    }
}

package br.com.candidatodebolso.webservice.persistence.model.pollintention;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Researcher extends AbstractEntity {

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "researcher")
    private List<PollIntention> pollIntentions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PollIntention> getPollIntentions() {
        return pollIntentions;
    }

    public void setPollIntentions(List<PollIntention> pollIntentions) {
        this.pollIntentions = pollIntentions;
    }
}

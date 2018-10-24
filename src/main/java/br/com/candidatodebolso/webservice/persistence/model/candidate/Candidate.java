package br.com.candidatodebolso.webservice.persistence.model.candidate;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;
import br.com.candidatodebolso.webservice.persistence.model.question.Answer;
import br.com.candidatodebolso.webservice.persistence.model.question.Question;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Candidate extends AbstractEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne(optional = false)
    private PoliticalParty politicalParty;

    @Column(nullable = false)
    private String photoUrl;

    @ElementCollection
    private Map<Question, Answer> answers = new HashMap<>();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Map<Question, Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Question, Answer> answers) {
        this.answers = answers;
    }

    public PoliticalParty getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(PoliticalParty politicalParty) {
        this.politicalParty = politicalParty;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

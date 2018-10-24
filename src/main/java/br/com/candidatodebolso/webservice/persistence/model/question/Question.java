package br.com.candidatodebolso.webservice.persistence.model.question;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Question extends AbstractEntity {

    @Column(nullable = false)
    private String question;
    @Transient
    private Answer answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}

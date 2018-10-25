package br.com.candidatodebolso.webservice.persistence.model.candidate;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;
import br.com.candidatodebolso.webservice.persistence.model.question.Answer;
import br.com.candidatodebolso.webservice.persistence.model.question.Question;

import javax.persistence.*;

@Entity
public class CandidateAnswerQuestion extends AbstractEntity {

    @ManyToOne(optional = false)
    private Question question;

    @Enumerated(value = EnumType.STRING)
    private Answer answer;

    @ManyToOne(optional = false)
    private Candidate candidate;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}

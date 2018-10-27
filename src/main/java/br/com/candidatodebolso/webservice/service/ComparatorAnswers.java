package br.com.candidatodebolso.webservice.service;

import br.com.candidatodebolso.webservice.persistence.model.candidate.Candidate;
import br.com.candidatodebolso.webservice.persistence.model.question.Question;

import java.util.List;

public interface ComparatorAnswers {

    Candidate compare(List<Question> questions);

    void forceUpdate();
}

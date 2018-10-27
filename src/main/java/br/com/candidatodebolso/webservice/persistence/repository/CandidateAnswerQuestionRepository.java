package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.candidate.CandidateAnswerQuestion;
import br.com.candidatodebolso.webservice.persistence.model.question.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CandidateAnswerQuestionRepository extends PagingAndSortingRepository<CandidateAnswerQuestion, Long> {

    Iterable<CandidateAnswerQuestion> findByQuestion(Question question);
}

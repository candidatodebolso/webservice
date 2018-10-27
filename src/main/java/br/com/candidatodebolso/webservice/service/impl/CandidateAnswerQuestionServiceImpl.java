package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.candidate.CandidateAnswerQuestion;
import br.com.candidatodebolso.webservice.persistence.model.question.Question;
import br.com.candidatodebolso.webservice.persistence.repository.CandidateAnswerQuestionRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateAnswerQuestionServiceImpl implements CrudService<CandidateAnswerQuestion> {

    private final CandidateAnswerQuestionRepository candidateAnswerQuestionRepository;

    @Autowired
    public CandidateAnswerQuestionServiceImpl(CandidateAnswerQuestionRepository candidateAnswerQuestionRepository) {
        this.candidateAnswerQuestionRepository = candidateAnswerQuestionRepository;
    }

    @Override
    public CandidateAnswerQuestion getById(Long id) {
        return candidateAnswerQuestionRepository.findById(id).orElseThrow(() -> new NotFoundException("Essa resposta não existe"));
    }

    @Override
    public CandidateAnswerQuestion create(CandidateAnswerQuestion object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return candidateAnswerQuestionRepository.save(object);
    }

    @Override
    public CandidateAnswerQuestion update(CandidateAnswerQuestion object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return candidateAnswerQuestionRepository.save(object);
    }

    @Override
    public void delete(CandidateAnswerQuestion object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        candidateAnswerQuestionRepository.delete(getById(object.getId()));
    }

    public Iterable<CandidateAnswerQuestion> findByQuestion(Question question) {
        AbstractEntityUtil.checkIfIdIsNotNull(question);
        return candidateAnswerQuestionRepository.findByQuestion(question);
    }

    @Override
    public Iterable<CandidateAnswerQuestion> listAll() {
        return candidateAnswerQuestionRepository.findAll();
    }
}

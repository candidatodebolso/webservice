package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.candidate.Candidate;
import br.com.candidatodebolso.webservice.persistence.model.question.Question;
import br.com.candidatodebolso.webservice.persistence.repository.CandidateRepository;
import br.com.candidatodebolso.webservice.persistence.repository.QuestionRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements CrudService<Question> {

    private final QuestionRepository candidateRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Question getById(Long id) {
        return candidateRepository.findById(id).orElseThrow(() -> new NotFoundException("Essa questão não existe"));
    }

    @Override
    public Question create(Question object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return candidateRepository.save(object);
    }

    @Override
    public Question update(Question object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return candidateRepository.save(object);
    }

    @Override
    public void delete(Question object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        candidateRepository.delete(getById(object.getId()));
    }

    @Override
    public Iterable<Question> listAll() {
        return candidateRepository.findAll();
    }
}

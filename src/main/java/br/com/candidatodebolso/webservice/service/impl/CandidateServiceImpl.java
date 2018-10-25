package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.candidate.Candidate;
import br.com.candidatodebolso.webservice.persistence.repository.CandidateRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CrudService<Candidate> {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate getById(Long id) {
        return candidateRepository.findById(id).orElseThrow(() -> new NotFoundException("Esse candidato não existe"));
    }

    @Override
    public Candidate create(Candidate object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return candidateRepository.save(object);
    }

    @Override
    public Candidate update(Candidate object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return candidateRepository.save(object);
    }

    @Override
    public void delete(Candidate object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        candidateRepository.delete(getById(object.getId()));
    }

    @Override
    public Iterable<Candidate> listAll() {
        return candidateRepository.findAll();
    }
}

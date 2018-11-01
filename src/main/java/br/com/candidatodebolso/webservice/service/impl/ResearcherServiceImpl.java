package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.pollintention.Researcher;
import br.com.candidatodebolso.webservice.persistence.repository.ResearcherRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResearcherServiceImpl implements CrudService<Researcher> {

    private final ResearcherRepository researcherRepository;

    @Autowired
    public ResearcherServiceImpl(ResearcherRepository pollIntentionRepository) {
        this.researcherRepository = pollIntentionRepository;
    }

    @Override
    public Researcher getById(Long id) {
        return researcherRepository.findById(id).orElseThrow(() -> new NotFoundException("Essa pesquisa não existe"));
    }

    @Override
    public Researcher create(Researcher object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return researcherRepository.save(object);
    }

    @Override
    public Researcher update(Researcher object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return researcherRepository.save(object);
    }

    @Override
    public void delete(Researcher object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        researcherRepository.delete(getById(object.getId()));
    }

    @Override
    public Iterable<Researcher> listAll() {
        return researcherRepository.findAll();
    }

}

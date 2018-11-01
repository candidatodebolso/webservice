package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.pollintention.PollIntention;
import br.com.candidatodebolso.webservice.persistence.model.pollintention.Researcher;
import br.com.candidatodebolso.webservice.persistence.repository.PollIntentionRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollIntentionServiceImpl implements CrudService<PollIntention> {

    private final PollIntentionRepository pollIntentionRepository;

    @Autowired
    public PollIntentionServiceImpl(PollIntentionRepository pollIntentionRepository) {
        this.pollIntentionRepository = pollIntentionRepository;
    }

    @Override
    public PollIntention getById(Long id) {
        return pollIntentionRepository.findById(id).orElseThrow(() -> new NotFoundException("Essa pesquisa não existe"));
    }

    @Override
    public PollIntention create(PollIntention object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return pollIntentionRepository.save(object);
    }

    @Override
    public PollIntention update(PollIntention object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return pollIntentionRepository.save(object);
    }

    @Override
    public void delete(PollIntention object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        pollIntentionRepository.delete(getById(object.getId()));
    }

    @Override
    public Iterable<PollIntention> listAll() {
        return pollIntentionRepository.findAll();
    }

    public Iterable<PollIntention> findByResearcher(Researcher researcher) {
        AbstractEntityUtil.checkIfIdIsNotNull(researcher);
        return pollIntentionRepository.findByResearcher(researcher);
    }
}

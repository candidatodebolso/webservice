package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.candidate.PoliticalParty;
import br.com.candidatodebolso.webservice.persistence.repository.PoliticalPartyRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliticalPartyServiceImpl implements CrudService<PoliticalParty> {

    private final PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    public PoliticalPartyServiceImpl(PoliticalPartyRepository politicalPartyRepository) {
        this.politicalPartyRepository = politicalPartyRepository;
    }

    @Override
    public PoliticalParty getById(Long id) {
        return politicalPartyRepository.findById(id).orElseThrow(() -> new NotFoundException("Esse partido não existe"));
    }

    @Override
    public PoliticalParty create(PoliticalParty object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return politicalPartyRepository.save(object);
    }

    @Override
    public PoliticalParty update(PoliticalParty object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return politicalPartyRepository.save(object);
    }

    @Override
    public void delete(PoliticalParty object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        politicalPartyRepository.delete(getById(object.getId()));
    }

    @Override
    public Iterable<PoliticalParty> listAll() {
        return politicalPartyRepository.findAll();
    }
}

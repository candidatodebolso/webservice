package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.news.Portal;
import br.com.candidatodebolso.webservice.persistence.repository.PortalRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalServiceImpl implements CrudService<Portal> {

    private final PortalRepository portalRepository;

    @Autowired
    public PortalServiceImpl(PortalRepository portalRepository) {
        this.portalRepository = portalRepository;
    }

    @Override
    public Portal getById(Long id) {
        return portalRepository.findById(id).orElseThrow(() -> new NotFoundException("Esse portal de noticias não existe"));

    }

    @Override
    public Portal create(Portal object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return portalRepository.save(object);
    }

    @Override
    public Portal update(Portal object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return portalRepository.save(object);
    }

    @Override
    public void delete(Portal object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        portalRepository.delete(getById(object.getId()));
    }

    @Override
    public Iterable<Portal> listAll() {
        return portalRepository.findAll();
    }
}

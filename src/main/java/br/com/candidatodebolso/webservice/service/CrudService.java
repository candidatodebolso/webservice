package br.com.candidatodebolso.webservice.service;

import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;

public interface CrudService<T extends AbstractEntity> {

    T getById(Long id);

    T create(T object);

    T update(T object);

    void delete(T object);

    Iterable<T> listAll();
}

package br.com.candidatodebolso.webservice.util;

import br.com.candidatodebolso.webservice.exception.BadRequestException;
import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.AbstractEntity;

public class AbstractEntityUtil {

    public static void checkIfIdIsNull(AbstractEntity entity) {
        if (entity.getId() != null) {
            throw new BadRequestException("Entidade invalida");
        }
    }

    public static void checkIfIdIsNotNull(AbstractEntity entity) {
        if (entity.getId() == null) {
            throw new NotFoundException("Essa entidade não existe");
        }
    }
}

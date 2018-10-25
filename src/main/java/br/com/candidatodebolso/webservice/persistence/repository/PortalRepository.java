package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.news.Portal;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PortalRepository extends PagingAndSortingRepository<Portal, Long> {
}

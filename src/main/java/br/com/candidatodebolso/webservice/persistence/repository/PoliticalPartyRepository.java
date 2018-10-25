package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.candidate.PoliticalParty;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PoliticalPartyRepository extends PagingAndSortingRepository<PoliticalParty, Long> {
}

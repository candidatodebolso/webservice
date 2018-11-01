package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.pollintention.Researcher;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResearcherRepository extends PagingAndSortingRepository<Researcher, Long> {

}

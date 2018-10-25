package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.candidate.Candidate;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CandidateRepository extends PagingAndSortingRepository<Candidate, Long> {
}

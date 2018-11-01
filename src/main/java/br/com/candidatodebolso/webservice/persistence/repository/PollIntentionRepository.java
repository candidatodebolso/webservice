package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.pollintention.PollIntention;
import br.com.candidatodebolso.webservice.persistence.model.pollintention.Researcher;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PollIntentionRepository extends PagingAndSortingRepository<PollIntention, Long> {

    Iterable<PollIntention> findByResearcher(Researcher researcher);
}

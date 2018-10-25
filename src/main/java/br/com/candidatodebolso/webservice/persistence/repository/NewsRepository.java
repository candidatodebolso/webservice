package br.com.candidatodebolso.webservice.persistence.repository;

import br.com.candidatodebolso.webservice.persistence.model.news.News;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {
}

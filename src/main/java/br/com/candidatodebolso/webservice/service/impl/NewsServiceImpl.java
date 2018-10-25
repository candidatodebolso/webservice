package br.com.candidatodebolso.webservice.service.impl;

import br.com.candidatodebolso.webservice.exception.NotFoundException;
import br.com.candidatodebolso.webservice.persistence.model.news.News;
import br.com.candidatodebolso.webservice.persistence.repository.NewsRepository;
import br.com.candidatodebolso.webservice.service.CrudService;
import br.com.candidatodebolso.webservice.util.AbstractEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements CrudService<News> {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News getById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NotFoundException("Essa noticia não existe"));

    }

    @Override
    public News create(News object) {
        AbstractEntityUtil.checkIfIdIsNull(object);
        return newsRepository.save(object);
    }

    @Override
    public News update(News object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        getById(object.getId());
        return newsRepository.save(object);
    }

    @Override
    public void delete(News object) {
        AbstractEntityUtil.checkIfIdIsNotNull(object);
        newsRepository.delete(getById(object.getId()));
    }

    @Override
    public Iterable<News> listAll() {
        return newsRepository.findAll();
    }
}

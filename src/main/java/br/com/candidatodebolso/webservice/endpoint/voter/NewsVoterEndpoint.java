package br.com.candidatodebolso.webservice.endpoint.voter;

import br.com.candidatodebolso.webservice.persistence.model.news.News;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("voter/lasted-news")
public class NewsVoterEndpoint {

    private final CrudService<News> newsCrudService;

    @Autowired
    public NewsVoterEndpoint(CrudService<News> newsCrudService) {
        this.newsCrudService = newsCrudService;
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(newsCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(newsCrudService.listAll(), HttpStatus.OK);
    }
}

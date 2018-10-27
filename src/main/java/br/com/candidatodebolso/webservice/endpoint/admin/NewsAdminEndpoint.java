package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.news.News;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/lasted-news")
public class NewsAdminEndpoint {

    private final CrudService<News> newsCrudService;

    @Autowired
    public NewsAdminEndpoint(CrudService<News> newsCrudService) {
        this.newsCrudService = newsCrudService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated News news) {
        return new ResponseEntity<>(newsCrudService.create(news), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated News news) {
        return new ResponseEntity<>(newsCrudService.update(news), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody News news) {
        newsCrudService.delete(news);
        return new ResponseEntity<>(HttpStatus.OK);
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

package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.question.Question;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/question")
public class QuestionAdminEndpoint {

    private final CrudService<Question> questionCrudService;

    @Autowired
    public QuestionAdminEndpoint(CrudService<Question> questionCrudService) {
        this.questionCrudService = questionCrudService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated Question question) {
        return new ResponseEntity<>(questionCrudService.create(question), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated Question question) {
        return new ResponseEntity<>(questionCrudService.update(question), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody Question question) {
        questionCrudService.delete(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(questionCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(questionCrudService.listAll(), HttpStatus.OK);
    }
}

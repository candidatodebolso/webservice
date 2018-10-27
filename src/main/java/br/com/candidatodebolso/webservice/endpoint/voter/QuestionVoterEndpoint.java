package br.com.candidatodebolso.webservice.endpoint.voter;

import br.com.candidatodebolso.webservice.persistence.model.question.Question;
import br.com.candidatodebolso.webservice.service.ComparatorAnswers;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("voter/question")
public class QuestionVoterEndpoint {

    private final CrudService<Question> questionCrudService;
    private final ComparatorAnswers comparatorAnswers;

    @Autowired
    public QuestionVoterEndpoint(CrudService<Question> questionCrudService, ComparatorAnswers comparatorAnswers) {
        this.questionCrudService = questionCrudService;
        this.comparatorAnswers = comparatorAnswers;
    }

    @PostMapping("send")
    @Transactional
    public ResponseEntity<?> compareAnswers(@RequestBody List<Question> questions) {
        return new ResponseEntity<>(comparatorAnswers.compare(questions), HttpStatus.OK);
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

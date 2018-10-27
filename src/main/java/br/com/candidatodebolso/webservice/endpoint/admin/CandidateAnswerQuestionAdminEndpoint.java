package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.candidate.CandidateAnswerQuestion;
import br.com.candidatodebolso.webservice.service.ComparatorAnswers;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/candidate-answer")
public class CandidateAnswerQuestionAdminEndpoint {

    private final CrudService<CandidateAnswerQuestion> candidateAnswerQuestionCrudService;
    private final ComparatorAnswers comparatorAnswers;

    @Autowired
    public CandidateAnswerQuestionAdminEndpoint(CrudService<CandidateAnswerQuestion> candidateAnswerQuestionCrudService, ComparatorAnswers comparatorAnswers) {
        this.candidateAnswerQuestionCrudService = candidateAnswerQuestionCrudService;
        this.comparatorAnswers = comparatorAnswers;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated CandidateAnswerQuestion candidateAnswerQuestion) {
        candidateAnswerQuestion = candidateAnswerQuestionCrudService.create(candidateAnswerQuestion);
        comparatorAnswers.forceUpdate();
        return new ResponseEntity<>(candidateAnswerQuestion, HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated CandidateAnswerQuestion candidateAnswerQuestion) {
        candidateAnswerQuestion = candidateAnswerQuestionCrudService.update(candidateAnswerQuestion);
        comparatorAnswers.forceUpdate();
        return new ResponseEntity<>(candidateAnswerQuestion, HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody CandidateAnswerQuestion candidateAnswerQuestion) {
        candidateAnswerQuestionCrudService.delete(candidateAnswerQuestion);
        comparatorAnswers.forceUpdate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(candidateAnswerQuestionCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(candidateAnswerQuestionCrudService.listAll(), HttpStatus.OK);
    }
}

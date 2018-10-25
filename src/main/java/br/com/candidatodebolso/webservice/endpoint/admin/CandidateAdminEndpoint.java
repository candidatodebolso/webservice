package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.candidate.Candidate;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/candidate")
public class CandidateAdminEndpoint {

    private final CrudService<Candidate> candidateCrudService;

    @Autowired
    public CandidateAdminEndpoint(CrudService<Candidate> candidateCrudService) {
        this.candidateCrudService = candidateCrudService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated Candidate candidate) {
        return new ResponseEntity<>(candidateCrudService.create(candidate), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated Candidate candidate) {
        return new ResponseEntity<>(candidateCrudService.update(candidate), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody Candidate candidate) {
        candidateCrudService.delete(candidate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(candidateCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(candidateCrudService.listAll(), HttpStatus.OK);
    }
}

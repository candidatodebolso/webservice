package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.pollintention.PollIntention;
import br.com.candidatodebolso.webservice.persistence.model.pollintention.Researcher;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/poll-intention")
public class PollIntentionAdminEndpoint {

    private final CrudService<PollIntention> pollIntentionCrudService;

    @Autowired
    public PollIntentionAdminEndpoint(CrudService<PollIntention> pollIntentionCrudService) {
        this.pollIntentionCrudService = pollIntentionCrudService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated PollIntention pollIntention) {
        return new ResponseEntity<>(pollIntentionCrudService.create(pollIntention), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated PollIntention pollIntention) {
        return new ResponseEntity<>(pollIntentionCrudService.update(pollIntention), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody PollIntention pollIntention) {
        pollIntentionCrudService.delete(pollIntention);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(pollIntentionCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(pollIntentionCrudService.listAll(), HttpStatus.OK);
    }
}

package br.com.candidatodebolso.webservice.endpoint.admin;

import br.com.candidatodebolso.webservice.persistence.model.candidate.PoliticalParty;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/political-party")
public class PoliticalPartyAdminEndpoint {

    private final CrudService<PoliticalParty> politicalPartyCrudService;

    @Autowired
    public PoliticalPartyAdminEndpoint(CrudService<PoliticalParty> politicalPartyCrudService) {
        this.politicalPartyCrudService = politicalPartyCrudService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Validated PoliticalParty politicalParty) {
        return new ResponseEntity<>(politicalPartyCrudService.create(politicalParty), HttpStatus.CREATED);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Validated PoliticalParty politicalParty) {
        return new ResponseEntity<>(politicalPartyCrudService.update(politicalParty), HttpStatus.OK);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> delete(@RequestBody PoliticalParty politicalParty) {
        politicalPartyCrudService.delete(politicalParty);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Transactional
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(politicalPartyCrudService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    @Transactional
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(politicalPartyCrudService.listAll(), HttpStatus.OK);
    }
}

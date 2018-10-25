package br.com.candidatodebolso.webservice.endpoint.voter;

import br.com.candidatodebolso.webservice.persistence.model.candidate.PoliticalParty;
import br.com.candidatodebolso.webservice.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("voter/political-party")
public class PoliticalPartyVoterEndpoint {

    private final CrudService<PoliticalParty> politicalPartyCrudService;

    @Autowired
    public PoliticalPartyVoterEndpoint(CrudService<PoliticalParty> politicalPartyCrudService) {
        this.politicalPartyCrudService = politicalPartyCrudService;
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
